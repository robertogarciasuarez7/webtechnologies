package es.uvt.lbd.wt.enlacesrest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "properties")
public class Properties {
	private String clientHost;
	private String jwtSecretKey;
	private Long jwtValidity;
	// identificador del enlace y extension
	private String imagesPath;

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getJwtSecretKey() {
		return jwtSecretKey;
	}

	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}

	public Long getJwtValidity() {
		return jwtValidity;
	}

	public void setJwtValidity(Long jwtValidity) {
		this.jwtValidity = jwtValidity;
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}
}
