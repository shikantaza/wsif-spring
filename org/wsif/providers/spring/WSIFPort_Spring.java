package org.wsif.providers.spring;

import java.io.Serializable;
import java.util.*;
import javax.wsdl.*;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;
import org.apache.wsif.WSIFException;
import org.apache.wsif.WSIFOperation;
import org.apache.wsif.base.WSIFDefaultPort;
import org.apache.wsif.logging.Trc;
import org.apache.wsif.providers.WSIFDynamicTypeMap;
import org.apache.wsif.util.WSIFUtils;
import org.apache.wsif.wsdl.extensions.format.TypeMap;
import org.apache.wsif.wsdl.extensions.format.TypeMapping;
import org.wsif.wsdl.extensions.spring.SpringAddress;

import org.springframework.core.io.ClassPathResource ;
import org.springframework.beans.factory.xml.XmlBeanFactory ;

public class WSIFPort_Spring extends WSIFDefaultPort
    implements Serializable
{

    public WSIFPort_Spring(Definition definition, Port port, WSIFDynamicTypeMap wsifdynamictypemap)
        throws WSIFException
    {
        fieldDefinition = null;
        fieldPortModel = null;
        fieldObjectReference = null;
        fieldTypeMaps = new HashMap();
        operationInstances = new HashMap();
        Trc.entry(this, definition, port, wsifdynamictypemap);
        fieldDefinition = definition;
        fieldPortModel = port;
        buildTypeMap();
        if(Trc.ON)
            Trc.exit(deep());
    }

    private void buildTypeMap()
        throws WSIFException
    {
        Trc.entry(this);
        TypeMapping typemapping = null;
        Iterator iterator = fieldPortModel.getBinding().getExtensibilityElements().iterator();
        while(iterator.hasNext()) 
        {
            Object obj = iterator.next();
            if(!(obj instanceof TypeMapping))
                continue;
            typemapping = (TypeMapping)obj;
            if("Java".equals(typemapping.getEncoding()) && "Java".equals(typemapping.getStyle()))
                break;
            typemapping = null;
        }
        if(typemapping == null)
        {
            QName qname = fieldPortModel.getBinding().getQName();
            throw new WSIFException("Binding " + (qname != null ? qname.toString() : "<null>") + " does not contain a typeMap with encoding=Java and style=Java");
        }
        for(Iterator iterator1 = typemapping.getMaps().iterator(); iterator1.hasNext();)
        {
            TypeMap typemap = (TypeMap)iterator1.next();
            QName qname1 = typemap.getTypeName();
            if(qname1 == null)
                qname1 = typemap.getElementName();
            String s = typemap.getFormatType();
            if(qname1 != null && s != null)
            {
                if(fieldTypeMaps.containsKey(qname1))
                {
                    Vector vector = null;
                    Object obj1 = fieldTypeMaps.get(qname1);
                    if(obj1 instanceof Vector)
                    {
                        vector = (Vector)obj1;
                    } else
                    {
                        vector = new Vector();
                        vector.addElement(obj1);
                    }
                    vector.addElement(s);
                    fieldTypeMaps.put(qname1, vector);
                } else
                {
                    fieldTypeMaps.put(qname1, s);
                }
            } else
            {
                throw new WSIFException("Error in binding TypeMap. Key or Value is null");
            }
        }

        Trc.exit();
    }

    public WSIFOperation createOperation(String s)
        throws WSIFException
    {
        Trc.entry(this);
        WSIFOperation wsifoperation = createOperation(s, null, null);
        Trc.exit(wsifoperation);
        return wsifoperation;
    }

    public WSIFOperation createOperation(String s, String s1, String s2)
        throws WSIFException
    {
        Trc.entry(this, s, s1, s2);
        WSIFOperation_Spring wsifoperation_spring = getDynamicWSIFOperation(s, s1, s2);
        if(wsifoperation_spring == null)
        {
            throw new WSIFException("Could not create operation: " + s + ":" + s1 + ":" + s2);
        } else
        {
            WSIFOperation_Spring wsifoperation_spring1 = wsifoperation_spring.copy();
            Trc.exit(wsifoperation_spring1);
            return wsifoperation_spring1;
        }
    }

    public String deep()
    {
        String s = "";
        try
        {
            s = new String(super.toString() + ":\n");
            s = s + ("definition:" + fieldDefinition != null ? fieldDefinition.getQName() != null ? fieldDefinition.getQName().toString() : "unknown" : "null");
            s = s + (" portModel:" + fieldPortModel != null ? fieldPortModel.getName() != null ? fieldPortModel.getName().toString() : "unknown" : "null");
            s = s + " objectReference:" + fieldObjectReference;
            s = s + " operationInstances:";
            if(operationInstances == null)
            {
                s = s + "null";
            } else
            {
                s = s + " size:" + operationInstances.size();
                Iterator iterator = operationInstances.keySet().iterator();
                for(int i = 0; iterator.hasNext(); i++)
                {
                    String s1 = (String)iterator.next();
                    WSIFOperation_Spring wsifoperation_spring = (WSIFOperation_Spring)operationInstances.get(s1);
                    s = s + "\noperationInstances[" + i + "]:" + s1 + " " + wsifoperation_spring + " ";
                }

            }
        }
        catch(Exception exception)
        {
            Trc.exceptionInTrace(exception);
        }
        return s;
    }

    public Definition getDefinition()
    {
        Trc.entry(this);
        Trc.exit(fieldDefinition);
        return fieldDefinition;
    }

    protected WSIFOperation_Spring getDynamicWSIFOperation(String s, String s1, String s2)
        throws WSIFException
    {
        Trc.entry(this, s, s1, s2);
        WSIFOperation_Spring wsifoperation_spring = (WSIFOperation_Spring)operationInstances.get(getKey(s, s1, s2));
        if(wsifoperation_spring == null)
        {
            javax.wsdl.BindingOperation bindingoperation = WSIFUtils.getBindingOperation(fieldPortModel.getBinding(), s, s1, s2);
            if(bindingoperation != null)
            {
                wsifoperation_spring = new WSIFOperation_Spring(fieldPortModel, bindingoperation, this, fieldTypeMaps);
                setDynamicWSIFOperation(s, s1, s2, wsifoperation_spring);
            }
        }
        Trc.exit(wsifoperation_spring);
        return wsifoperation_spring;
    }

    public Object getObjectReference()
        throws WSIFException
    {
        Trc.entry(this);
        if(fieldObjectReference == null)
        {
            SpringAddress springaddress = null;
            try
            {
                ExtensibilityElement extensibilityelement = (ExtensibilityElement)fieldPortModel.getExtensibilityElements().get(0);
                
                if(extensibilityelement == null)
                    throw new WSIFException("missing port extension");
                
                springaddress = (SpringAddress)extensibilityelement;
                
                XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource(springaddress.getXmlFileName()));
                fieldObjectReference = bf.getBean(springaddress.getBeanName()) ;
            }
            catch(Exception exception)
            {
                Trc.exception(exception);
                throw new WSIFException("Could not create Spring bean with name '" + springaddress.getBeanName() + "'", exception);
            }
        }
        Trc.exit(fieldObjectReference);
        return fieldObjectReference;
    }

    public Port getPortModel()
    {
        Trc.entry(this);
        Trc.exit(fieldPortModel);
        return fieldPortModel;
    }

    public void setDefinition(Definition definition)
    {
        Trc.entry(this, definition);
        fieldDefinition = definition;
        Trc.exit();
    }

    public void setDynamicWSIFOperation(String s, String s1, String s2, WSIFOperation_Spring wsifoperation_spring)
    {
        Trc.entry(this, s, s1, s2, wsifoperation_spring);
        operationInstances.put(getKey(s, s1, s2), wsifoperation_spring);
        Trc.exit();
    }

    public void setObjectReference(Object obj)
    {
        Trc.entry(this, obj);
        fieldObjectReference = obj;
        Trc.exit();
    }

    public void setPortModel(Port port)
    {
        Trc.entry(this, port);
        fieldPortModel = port;
        Trc.exit();
    }

    private static final long serialVersionUID = 1L;
    private Definition fieldDefinition;
    private Port fieldPortModel;
    private Object fieldObjectReference;
    private Map fieldTypeMaps;
    protected Map operationInstances;
}