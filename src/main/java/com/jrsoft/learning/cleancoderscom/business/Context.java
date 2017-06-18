package com.jrsoft.learning.cleancoderscom.business;

/*
 * This is Application Context.
 * The one and only singleton in the entire system!
 * Here all the injections happen
*/
public class Context {
    public static UserGateway userGateway;
    public static CodecastGateway codecastGateway;
    public static LicenseGateway licenseGateway;
    public static GateKeeper gateKeeper;
}
