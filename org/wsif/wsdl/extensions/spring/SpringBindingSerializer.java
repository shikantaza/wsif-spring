package org.wsif.wsdl.extensions.spring;

import com.ibm.wsdl.Constants;
import com.ibm.wsdl.util.StringUtils;
import com.ibm.wsdl.util.xml.DOMUtils;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.*;
import javax.xml.namespace.QName;
import org.apache.wsif.logging.Trc;
import org.w3c.dom.Element;

public class SpringBindingSerializer
    implements ExtensionSerializer, ExtensionDeserializer, Serializable
{

    public SpringBindingSerializer()
    {
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public void marshall(Class class1, QName qname, ExtensibilityElement extensibilityelement, PrintWriter printwriter, Definition definition, ExtensionRegistry extensionregistry)
        throws WSDLException
    {
        Trc.entry(this, class1, qname, extensibilityelement, printwriter, definition, extensionregistry);
        if(extensibilityelement == null)
        {
            Trc.exit();
            return;
        }
        if(extensibilityelement instanceof SpringBinding)
        {
            SpringBinding springbinding = (SpringBinding)extensibilityelement;
            printwriter.print("      <spring:binding");
            Boolean boolean1 = extensibilityelement.getRequired();
            if(boolean1 != null)
                DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED, boolean1.toString(), definition, printwriter);
            printwriter.println("/>");
        } else
        if(extensibilityelement instanceof SpringOperation)
        {
            SpringOperation springoperation = (SpringOperation)extensibilityelement;
            printwriter.print("      <spring:operation");
            if(springoperation.getMethodName() != null)
                DOMUtils.printAttribute("methodName", springoperation.getMethodName(), printwriter);
            if(springoperation.getMethodType() != null)
                DOMUtils.printAttribute("methodType", springoperation.getMethodType(), printwriter);
            if(springoperation.getParameterOrder() != null)
                DOMUtils.printAttribute("parameterOrder", StringUtils.getNMTokens(springoperation.getParameterOrder()), printwriter);
            if(springoperation.getReturnPart() != null)
                DOMUtils.printAttribute("returnPart", springoperation.getReturnPart(), printwriter);
            Boolean boolean2 = extensibilityelement.getRequired();
            if(boolean2 != null)
                DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED, boolean2.toString(), definition, printwriter);
            printwriter.println("/>");
        } else
        if(extensibilityelement instanceof SpringAddress)
        {
            SpringAddress springaddress = (SpringAddress)extensibilityelement;
            printwriter.print("      <spring:address");
            if(springaddress.getInterfaceName() != null)
                DOMUtils.printAttribute("interfaceName", springaddress.getInterfaceName(), printwriter);
            if(springaddress.getBeanName() != null)
                DOMUtils.printAttribute("beanName", springaddress.getBeanName(), printwriter);
            if(springaddress.getXmlFileName() != null)
                DOMUtils.printAttribute("xmlFileName", springaddress.getXmlFileName(), printwriter);
            Boolean boolean3 = extensibilityelement.getRequired();
            if(boolean3 != null)
                DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED, boolean3.toString(), definition, printwriter);
            printwriter.println("/>");
        }
        Trc.exit();
    }

    public void registerSerializer(ExtensionRegistry extensionregistry)
    {
        Trc.entry(this, extensionregistry);
        extensionregistry.registerSerializer(javax.wsdl.Binding.class, SpringBindingConstants.Q_ELEM_SPRING_BINDING, this);
        extensionregistry.registerDeserializer(javax.wsdl.Binding.class, SpringBindingConstants.Q_ELEM_SPRING_BINDING, this);
        extensionregistry.mapExtensionTypes(javax.wsdl.Binding.class, SpringBindingConstants.Q_ELEM_SPRING_BINDING, org.wsif.wsdl.extensions.spring.SpringBinding.class);
        extensionregistry.registerSerializer(javax.wsdl.BindingOperation.class, SpringBindingConstants.Q_ELEM_SPRING_OPERATION, this);
        extensionregistry.registerDeserializer(javax.wsdl.BindingOperation.class, SpringBindingConstants.Q_ELEM_SPRING_OPERATION, this);
        extensionregistry.mapExtensionTypes(javax.wsdl.BindingOperation.class, SpringBindingConstants.Q_ELEM_SPRING_OPERATION, org.wsif.wsdl.extensions.spring.SpringOperation.class);
        extensionregistry.registerSerializer(javax.wsdl.Port.class, SpringBindingConstants.Q_ELEM_SPRING_ADDRESS, this);
        extensionregistry.registerDeserializer(javax.wsdl.Port.class, SpringBindingConstants.Q_ELEM_SPRING_ADDRESS, this);
        extensionregistry.mapExtensionTypes(javax.wsdl.Port.class, SpringBindingConstants.Q_ELEM_SPRING_ADDRESS, org.wsif.wsdl.extensions.spring.SpringAddress.class);
        Trc.exit();
    }

    public ExtensibilityElement unmarshall(Class class1, QName qname, Element element, Definition definition, ExtensionRegistry extensionregistry)
        throws WSDLException
    {
        Trc.entry(this, class1, qname, element, definition, extensionregistry);
        ExtensibilityElement extensibilityelement = null;
        if(SpringBindingConstants.Q_ELEM_SPRING_BINDING.equals(qname))
        {
            SpringBinding springbinding = new SpringBinding();
            Trc.exit(springbinding);
            return springbinding;
        }
        if(SpringBindingConstants.Q_ELEM_SPRING_OPERATION.equals(qname))
        {
            SpringOperation springoperation = new SpringOperation();
            String s = DOMUtils.getAttribute(element, "methodName");
            if(s != null)
                springoperation.setMethodName(s);
            String s2 = DOMUtils.getAttribute(element, "methodType");
            if(s2 != null)
                springoperation.setMethodType(s2);
            String s4 = DOMUtils.getAttribute(element, "parameterOrder");
            if(s4 != null)
                springoperation.setParameterOrder(s4);
            String s6 = DOMUtils.getAttribute(element, "returnPart");
            if(s6 != null)
                springoperation.setReturnPart(s6);
            Trc.exit(springoperation);
            return springoperation;
        }
        if(SpringBindingConstants.Q_ELEM_SPRING_ADDRESS.equals(qname))
        {
            SpringAddress springaddress = new SpringAddress();
            String s1 = DOMUtils.getAttribute(element, "interfaceName");
            if(s1 != null)
                springaddress.setInterfaceName(s1);
            String s3 = DOMUtils.getAttribute(element, "beanName");
            if(s3 != null)
                springaddress.setBeanName(s3);
            String s5 = DOMUtils.getAttribute(element, "xmlFileName");
            if(s5 != null)
                springaddress.setXmlFileName(s5);
            Trc.exit(springaddress);
            return springaddress;
        } else
        {
            Trc.exit(extensibilityelement);
            return extensibilityelement;
        }
    }

    private static final long serialVersionUID = 1L;
}