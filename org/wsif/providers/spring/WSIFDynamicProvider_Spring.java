package org.wsif.providers.spring;

import java.util.Iterator;
import java.util.List;
import javax.wsdl.*;
import org.apache.wsif.WSIFException;
import org.apache.wsif.WSIFPort;
import org.apache.wsif.logging.Trc;
import org.apache.wsif.providers.WSIFDynamicTypeMap;
import org.apache.wsif.spi.WSIFProvider;
import org.wsif.wsdl.extensions.spring.SpringBinding;

import org.apache.wsif.base.WSIFServiceImpl ;

public class WSIFDynamicProvider_Spring
    implements WSIFProvider
{

    public WSIFDynamicProvider_Spring()
    {
        Trc.entry(this);
        
        // new code added
        WSIFServiceImpl.addExtensionRegistry(new org.wsif.wsdl.extensions.spring.SpringExtensionRegistry());
        // end new code added
        
        Trc.exit();
    }

    public WSIFPort createDynamicWSIFPort(Definition definition, Service service, Port port, WSIFDynamicTypeMap wsifdynamictypemap)
        throws WSIFException
    {
        Trc.entry(this, definition, service, port, wsifdynamictypemap);
        Binding binding = port.getBinding();
        List list = binding.getExtensibilityElements();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Object obj = iterator.next();
            if(obj instanceof SpringBinding)
            {
                WSIFPort_Spring wsifport_spring = new WSIFPort_Spring(definition, port, wsifdynamictypemap);
                Trc.exit((Object)wsifport_spring);
                return wsifport_spring;
            }
        }

        Trc.exit();
        return null;
    }

    public String[] getAddressNamespaceURIs()
    {
        Trc.entry(this);
        Trc.exit(supportedAddressNamespaceURIs);
        return supportedAddressNamespaceURIs;
    }

    public String[] getBindingNamespaceURIs()
    {
        Trc.entry(this);
        Trc.exit(supportedBindingNamespaceURIs);
        return supportedBindingNamespaceURIs;
    }

    private static final String supportedBindingNamespaceURIs[] = {
        "http://xyz.com/wsdl/spring/"
    };
    private static final String supportedAddressNamespaceURIs[] = {
        "http://xyz.com/wsdl/spring/"
    };

}