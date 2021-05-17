import org.springframework.web.client.RestTemplate;
public class Usuario_sysRequestImpl implements Usuario_sysRequest{

 private RestTemplate restTemplate;


public void setFk_id_usuario_sys(Usuario_sys fk_id_usuario_sys,int int){
 restTemplate.put('http://teste/Log_sys/{id}/Usuario_sys/setFk_id_usuario_sys',fk_id_usuario_sys,int);
 return ;
}


public Usuario_sys getFk_id_usuario_sys(int int){
 Usuario_sys aux = restTemplate.getForObject('http://teste/Log_sys/{id}/Usuario_sys/getFk_id_usuario_sys',Usuario_sys.class,int);
return aux;
}


}