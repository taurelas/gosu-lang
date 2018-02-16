/*
 * Copyright 2014 Guidewire Software, Inc.
 */

package gw.lang.ir;

import gw.lang.GosuShop;
import gw.lang.UnstableAPI;

@UnstableAPI
public abstract class IRElement {
  private IRElement _parent;
  private int _iLineNumber;
  private boolean _bImplicit;

  protected IRElement() {
    _iLineNumber = -1;
  }

  public IRElement getParent() {
    return _parent;
  }

  public void setParent( IRElement parent ) {
    _parent = parent;
  }

  protected void setParentToThis( IRElement element ) {
    if (element != null) {
      element.setParent( this );
    }
  }

  public boolean isImplicit() {
    return _bImplicit || (getParent() != null && getParent().isImplicit());
  }
  public void setImplicit( boolean bImplicit ) {
    _bImplicit = bImplicit;
  }

  public int getLineNumber() {
    if( !isImplicit() ) {
      return _iLineNumber;
    }
    return -1;
  }
  public void setLineNumber( int iLineNumber ) {
    if( !isImplicit() ) {
      _iLineNumber = iLineNumber;
    }
  }

  public static IRType maybeEraseStructuralType( IRType type ) {
    return maybeEraseStructuralType( null, type );
  }
  public static IRType maybeEraseStructuralType( IRType ownersType, IRType type ) {
    IRType originalType = type;
    int iArrayDims = 0;
    while( type.isArray() ) {
      iArrayDims++;
      type = type.getComponentType();
    }
    if( ownersType == null ? type.isStructural() : type.isStructuralAndErased( ownersType ) ) {
      type = GosuShop.getIRTypeResolver().getDescriptor( Object.class );
      while( iArrayDims-- > 0 ) {
        type = type.getArrayType();
      }
    }
    else {
      type = originalType;
    }
    return type;
  }
}
