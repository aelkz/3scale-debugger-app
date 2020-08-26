package com.redhat.threescale.debug.controller;

import com.redhat.threescale.debug.model.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.management.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class IndexController {

    @Autowired
    Environment environment;

    @Autowired
    ServletContext servletContext;

    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, Principal principal) {
        final String message = "Hello" + Optional.ofNullable(principal)
                .map(Principal::getName)
                .orElse("Spring Security");
        modelAndView.addObject("message", message);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/debug")
    public Debug getServerStatus(@RequestHeader Map<String,String> headers){
        Debug obj = new Debug();
        List<String> tempList = new ArrayList<>();

        // server information
        obj.setHost_app_server(servletContext.getServerInfo());
        obj.setHost_app_servlet_version(servletContext.getEffectiveMajorVersion() + "." + servletContext.getEffectiveMinorVersion());
        obj.setHost_virtual_server_name(servletContext.getVirtualServerName());
        obj.setContext_path(servletContext.getContextPath());
        obj.setRequest_url(httpServletRequest.getRequestURL().toString());
        obj.setRequest_uri(httpServletRequest.getRequestURI());
        obj.setProtocol(httpServletRequest.getProtocol());
        obj.setScheme(httpServletRequest.getScheme());
        obj.setLocal_port(String.valueOf(httpServletRequest.getLocalPort()));
        obj.setRequest_method(httpServletRequest.getMethod());

        try {
            obj.setRemote_port(""+httpServletRequest.getRemotePort());
        } catch (Exception ignored) { }

        // client information
        try {
            obj.setRemote_address(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            obj.setRemote_host(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        obj.setServer_name(httpServletRequest.getServerName());
        obj.setServer_port(String.valueOf(httpServletRequest.getServerPort()));

        // server system properties
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println(key + ": " + value);
            tempList.add(key + ": " + value);
        }

        obj.setSystem_properties(tempList);

        // server vm arguments
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        obj.setVm_arguments(arguments);

        // server ports information
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objs = null;
        try {
            objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            String hostname = InetAddress.getLocalHost().getHostName();
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            ArrayList<String> endPoints = new ArrayList<String>();
            for (ObjectName objx : objs) {
                String scheme = mbs.getAttribute(objx, "scheme").toString();
                String port = objx.getKeyProperty("port");
                for (InetAddress addr : addresses) {
                    String host = addr.getHostAddress();
                    String ep = scheme + "://" + host + ":" + port;
                    endPoints.add(ep);
                }
            }
            obj.setEndpoints(endPoints);
        } catch (MalformedObjectNameException | InstanceNotFoundException | UnknownHostException | ReflectionException | AttributeNotFoundException | MBeanException e) {
            e.printStackTrace();
        }

        if (headers != null && !headers.isEmpty()) {
            Map<String,String> values = new HashMap<>();
            headers.forEach((key, value) -> {
                System.out.println("Header Name: " + key + " Header Value: " + value);
                values.put(key,value);
            });
            obj.setHeaders(values);
        }

        return obj;
    }

}
