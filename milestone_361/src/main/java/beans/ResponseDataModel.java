package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDataModel extends ResponseModel {
	
	private User data;
	
	public ResponseDataModel(int status, String message, User data) {
		super();
		this.setStatus(status);
		this.setMessage(message);
		this.data = data;
	}
	
	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

}
