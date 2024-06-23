package NAGPSelenium.Base;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
	
	private Map<String,Object> _attributes;
	
	public TestContext()

	{
		_attributes=new HashMap<String, Object>();
	}
	public Map<String,Object> get_attributes() {
		return _attributes;
	}

	public void set_attribute(String key,Object value) {
		_attributes.put(key, value);
	}
	
	public Object get_attribute(String key) {
		return  _attributes.get(key);
	
	}
	
	public void remove_attribute(String key,Object value) {
		_attributes.remove(key, value);
	}

	
	
	

	
}
