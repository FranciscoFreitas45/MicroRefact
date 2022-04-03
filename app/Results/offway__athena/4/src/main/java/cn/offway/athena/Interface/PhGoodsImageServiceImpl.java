package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhGoodsImageService;
public class PhGoodsImageServiceImpl implements PhGoodsImageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


}