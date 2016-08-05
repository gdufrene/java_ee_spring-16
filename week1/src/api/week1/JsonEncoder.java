package api.week1;

public interface JsonEncoder {
  public String toJson( String str );
  public String toJson( Number n );
  public String toJson( java.util.Map map );
}
