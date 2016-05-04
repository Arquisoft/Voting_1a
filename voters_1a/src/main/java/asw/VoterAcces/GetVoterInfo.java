package asw.VoterAcces;

import org.springframework.web.bind.annotation.RequestBody;

import asw.model.Peticion;
import asw.model.UserInfo;

public interface GetVoterInfo {
	
	public UserInfo getVI(@RequestBody Peticion peticion);

}
