/*
 * Copyright 2014 Guidewire Software, Inc.
 */

package gw.lang.parser.expressions;

public interface IRelationalExpression extends IConditionalExpression
{
  String getOperator();
}
