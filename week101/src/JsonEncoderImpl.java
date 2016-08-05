public class JsonEncoderImpl implements api.week1.JsonEncoder {
  
  public String toJson( String str ) {
    // TODO: implement this method
    return "";
  }
  
  public String toJson( Number n ) {
    // TODO: implement this method
    return "0.0";
  }
  
  public String toJson( java.util.Map map ) {
    String ret = "{";
    boolean first = true;
    for( Object key : map.keySet() ) {
      if ( !first ) ret += ",";
      else first = false;
      Object value = map.get(key);
      
      // TODO: change this implementation.
      ret += "\"key\": \"value\"";
    }
    return ret + "}";
  }
  
  public static void main(String args[]) {
    JsonEncoderImpl enc = new JsonEncoderImpl();
    System.out.println( enc.toJson(12d) );
  }
}