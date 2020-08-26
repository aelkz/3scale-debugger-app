package com.redhat.threescale.debug.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Debug {
    private String host_app_server;
    private String host_app_servlet_version;
    private String host_virtual_server_name;
    private String request_url;
    private String protocol;
    private String request_method;
    private String request_uri;
    private String local_port;
    private String server_name;
    private String server_port;
    private String remote_host;
    private String remote_address;
    private String remote_port;
    private String scheme;
    private String context_path;
    private List<String> endpoints;
    private List<String> vm_arguments;
    private List<String> system_properties;
    private Map<String,String> headers;

    public Debug() {
        this.endpoints = Collections.emptyList();
        this.vm_arguments = Collections.emptyList();
        this.system_properties = Collections.emptyList();
        this.headers = Collections.emptyMap();
    }

    public Debug(String host_app_server, String host_app_servlet_version, String host_virtual_server_name, String local_port, String request_uri, String protocol, String remote_host, String remote_address, String scheme, String context_path, List<String> endpoints, String server_name, String server_port, String request_url, List<String> vm_arguments, List<String> system_properties, String request_method, String remote_port) {
        this.host_app_server = host_app_server;
        this.request_uri = request_uri;
        this.protocol = protocol;
        this.remote_host = remote_host;
        this.remote_address = remote_address;
        this.scheme = scheme;
        this.host_app_servlet_version = host_app_servlet_version;
        this.local_port = local_port;
        this.host_virtual_server_name = host_virtual_server_name;
        this.context_path = context_path;
        this.server_name = server_name;
        this.server_port = server_port;
        this.request_url = request_url;
        this.request_method = request_method;
        this.remote_port = remote_port;
        this.endpoints = Collections.emptyList();
        this.vm_arguments = Collections.emptyList();
        this.system_properties = Collections.emptyList();
        this.headers = Collections.emptyMap();
    }

    public String getRemote_port() {
        return remote_port;
    }

    public void setRemote_port(String remote_port) {
        this.remote_port = remote_port;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }

    public List<String> getSystem_properties() {
        return system_properties;
    }

    public void setSystem_properties(List<String> system_properties) {
        this.system_properties = system_properties;
    }

    public List<String> getVm_arguments() {
        return vm_arguments;
    }

    public void setVm_arguments(List<String> vm_arguments) {
        this.vm_arguments = vm_arguments;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getServer_port() {
        return server_port;
    }

    public void setServer_port(String server_port) {
        this.server_port = server_port;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    public String getContext_path() {
        return context_path;
    }

    public void setContext_path(String context_path) {
        this.context_path = context_path;
    }

    public String getHost_virtual_server_name() {
        return host_virtual_server_name;
    }

    public void setHost_virtual_server_name(String host_virtual_server_name) {
        this.host_virtual_server_name = host_virtual_server_name;
    }

    public String getLocal_port() {
        return local_port;
    }

    public void setLocal_port(String local_port) {
        this.local_port = local_port;
    }

    public String getHost_app_servlet_version() {
        return host_app_servlet_version;
    }

    public void setHost_app_servlet_version(String host_app_server_version) {
        this.host_app_servlet_version = host_app_server_version;
    }

    public String getHost_app_server() {
        return host_app_server;
    }

    public void setHost_app_server(String host_app_server) {
        this.host_app_server = host_app_server;
    }

    public String getRequest_uri() {
        return request_uri;
    }

    public void setRequest_uri(String request_uri) {
        this.request_uri = request_uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRemote_host() {
        return remote_host;
    }

    public void setRemote_host(String remote_host) {
        this.remote_host = remote_host;
    }

    public String getRemote_address() {
        return remote_address;
    }

    public void setRemote_address(String remote_address) {
        this.remote_address = remote_address;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Debug debug = (Debug) o;

        if (!request_uri.equals(debug.request_uri)) return false;
        return remote_host.equals(debug.remote_host);
    }

    @Override
    public int hashCode() {
        int result = request_uri.hashCode();
        result = 31 * result + remote_host.hashCode();
        return result;
    }
}
