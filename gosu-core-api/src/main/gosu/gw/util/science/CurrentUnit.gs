package gw.util.science
uses gw.util.Rational

final class CurrentUnit extends AbstractQuotientUnit<ChargeUnit, TimeUnit, Current, CurrentUnit> {
  final static var CACHE: UnitCache<CurrentUnit> = new UnitCache()

  public static var BASE: CurrentUnit = get( Coulomb, Second, 1, "Amperes", "A" )

  static function get( chargeUnit: ChargeUnit, timeUnit: TimeUnit, factor: Rational = null, name: String = null, symbol: String = null ) : CurrentUnit {
    var unit = new CurrentUnit( chargeUnit, timeUnit, factor, name, symbol )
    return CACHE.get( unit )
  }

  private construct( chargeUnit: ChargeUnit, timeUnit: TimeUnit, factor: Rational = null, name: String = null, symbol: String = null ) {
    super( chargeUnit, timeUnit, factor, name, symbol )
  }

  property get ChargeUnit() : ChargeUnit {
    return LeftUnit 
  }
  property get TimeUnit() : TimeUnit {
    return RightUnit 
  }
  
  function multiply( w: TimeUnit ) : ChargeUnit {
    return ChargeUnit
  }
}
