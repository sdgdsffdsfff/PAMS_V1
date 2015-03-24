package com.skynet.pams.app.frame;

import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.framework.action.BaseAction;

@IocBean
@At("/frame")
public class FrameAction extends BaseAction {
	@At("/home")
	@Ok("->:/home.ftl")
	public Map home() throws Exception {
		return ro;
	}
}
