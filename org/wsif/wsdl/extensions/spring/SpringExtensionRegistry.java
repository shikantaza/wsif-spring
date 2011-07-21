package org.wsif.wsdl.extensions.spring;

import javax.wsdl.extensions.ExtensionRegistry;
import org.apache.wsif.logging.Trc;

public class SpringExtensionRegistry extends ExtensionRegistry
{

    public SpringExtensionRegistry()
    {
        Trc.entry(this);
        SpringBindingSerializer springbindingserializer = new SpringBindingSerializer();
        springbindingserializer.registerSerializer(this);
        Trc.exit();
    }

    private static final long serialVersionUID = 1L;
}