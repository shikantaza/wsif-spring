package ora.wsif.wsdl.extensions.spring;

import java.io.Serializable;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;

public class SpringAddress
    implements ExtensibilityElement, Serializable
{

    public SpringAddress()
    {
        fieldElementType = SpringBindingConstants.Q_ELEM_SPRING_ADDRESS;
        fieldRequired = null;
    }

    public String getBeanName()
    {
        return beanName;
    }
    
    public String getXmlFileName()
    {
        return xmlFileName;
    }    
    
    public QName getElementType()
    {
        return fieldElementType;
    }

    public Boolean getRequired()
    {
        return fieldRequired;
    }

    public void setBeanName(String s)
    {
        beanName = s;
    }    

    public void setXmlFileName(String s)
    {
        xmlFileName = s;
    }
    
    public void setElementType(QName qname)
    {
        fieldElementType = qname;
    }

    public void setRequired(Boolean boolean1)
    {
        fieldRequired = boolean1;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append("\nSpringAddress (" + fieldElementType + "):");
        stringbuffer.append("\nrequired=" + fieldRequired);
        stringbuffer.append("\nbeanName=" + beanName);
        stringbuffer.append("\nxmlFileName=" + xmlFileName);
        return stringbuffer.toString();
    }

    private static final long serialVersionUID = 1L;
    protected QName fieldElementType;
    protected Boolean fieldRequired;
    
    protected String beanName;
    protected String xmlFileName;
    
}