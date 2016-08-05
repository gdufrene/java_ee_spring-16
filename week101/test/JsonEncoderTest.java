import static org.junit.Assert.*;

import org.junit.Test;
import api.week1.JsonEncoder;

public class JsonEncoderTest {
  
  JsonEncoder enc;
  
  public JsonEncoderTest() {
    enc = new JsonEncoderImpl();
  }
  
  @Test
  public void testString() {
    assertEquals( "\"hello world !\"", enc.toJson("hello world !") );
  }
  
  @Test
  public void testNumber() {
    assertEquals( "12.3", enc.toJson(12.3d) );
  }
  
  @Test
  public void testNumberInt() {
    assertEquals( "12.0", enc.toJson(12) );
  }
  
  @Test
  public void testStringQuote() {
    assertEquals( "\"hello \\\"world\\\" !\"", enc.toJson("hello \"world\" !") );
  }
  
  @Test
  public void testStringBackslash() {
    assertEquals( "\"This: \\\\ is a backslash\"", enc.toJson("This: \\ is a backslash") );
  }
  
  @Test
  public void testSimpleMap() {
    java.util.Map<Object, Object> map = new java.util.HashMap<>();
    map.put("a", 12);
    map.put("b", "12");
    assertEquals( "{\"a\": 12.0,\"b\": \"12\"}", enc.toJson(map) );
  }
  
  
}