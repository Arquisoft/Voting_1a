package asw;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import asw.DBManagement.GetVoterImpl;
import asw.VoterAcces.GetVoterInfo;
import asw.model.Peticion;
import asw.model.UserInfo;
import asw.model.UsuarioNoEncontrado;

@RestController
public class MainController implements GetVoterInfo {

    @RequestMapping(value="/user", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo getVI(@RequestBody Peticion peticion) {
        UserInfo result = getVR(peticion.getEmail(), peticion.getPassword());
        
        if (result != null)
        	return result;
        throw new UsuarioNoEncontrado();
    }

    public UserInfo getVR(String email, String password) {
    	return new GetVoterImpl().getVP(email, password);
	}

	@RequestMapping("/")
    public String landing() {
    	// Devolver una cadena informativa
        return "User Management Service";
    }
}
