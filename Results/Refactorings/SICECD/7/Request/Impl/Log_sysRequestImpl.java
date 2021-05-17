import org.springframework.web.client.RestTemplate;
public class Log_sysRequestImpl implements Log_sysRequest{

 private RestTemplate restTemplate;


public void setLog_sys_s(List<Log_sys> log_sys_s,int pk_id_usuario_sys){
 restTemplate.put('http://teste/Usuario_sys/{id}/Log_sys/setLog_sys_s',log_sys_s,pk_id_usuario_sys);
 return ;
}


public List<Log_sys> getLog_sys_s(int pk_id_usuario_sys){
 List<Log_sys> aux = restTemplate.getForObject('http://teste/Usuario_sys/{id}/Log_sys/getLog_sys_s',List<Log_sys>.class,pk_id_usuario_sys);
return aux;
}


}