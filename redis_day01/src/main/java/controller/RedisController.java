package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.RedisService;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("getValue")
	@ResponseBody
	public Object getValue() {
		return redisService.getValue("ygh");
	}

	@RequestMapping("set")
	@ResponseBody
	public void set(String key, String value) {
		redisService.set(key, value);
	}
}
