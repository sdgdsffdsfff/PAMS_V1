package com.skynet.pams.setup;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(packages = {"com.skynet.pams","com.skynet.app","com.pams"}, scanPackage = true)
@IocBy(type = ComboIocProvider.class, args={
	"*org.nutz.ioc.loader.json.JsonLoader", "ioc.js",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.skynet", "com.pams"})
public class MainModule {
	
}
