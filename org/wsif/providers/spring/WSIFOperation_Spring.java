package org.wsif.providers.spring;

import java.lang.reflect.*;
import java.util.*;
import javax.wsdl.*;
import javax.xml.namespace.QName;
import org.apache.wsif.*;
import org.apache.wsif.base.WSIFDefaultOperation;
import org.apache.wsif.logging.MessageLogger;
import org.apache.wsif.logging.Trc;
import org.apache.wsif.providers.ProviderUtils;
import org.wsif.wsdl.extensions.spring.SpringOperation;

public class WSIFOperation_Spring extends WSIFDefaultOperation
    implements WSIFOperation
{
    private class FaultMessageInfo
    {

        String fieldMessageName;
        String fieldPartName;
        String fieldFormatType;

        FaultMessageInfo(String s, String s1, String s2)
        {
            fieldMessageName = s;
            fieldPartName = s1;
            fieldFormatType = s2;
        }
    }


    public WSIFOperation_Spring(Port port, BindingOperation bindingoperation, WSIFPort_Spring wsifport_spring, Map map)
        throws WSIFException
    {
        fieldInParameterNames = null;
        fieldOutParameterNames = null;
        fieldFaultMessageInfos = null;
        fieldMethods = null;
        fieldConstructors = null;
        fieldOutputMessageName = null;
        fieldIsStatic = false;
        fieldIsConstructor = false;
        fieldTypeMaps = null;
        Trc.entry(this, port, bindingoperation, wsifport_spring, map);
        fieldPortModel = port;
        fieldBindingOperationModel = bindingoperation;
        fieldPort = wsifport_spring;
        fieldTypeMaps = map;
        Method amethod[] = fieldPort.getObjectReference().getClass().getMethods();
        try
        {
            fieldSpringOperationModel = (SpringOperation)fieldBindingOperationModel.getExtensibilityElements().get(0);
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            throw new WSIFException("Unable to resolve Spring binding for operation '" + bindingoperation.getName() + "'");
        }
        String s = fieldSpringOperationModel.getMethodType();
        if(s != null)
            if(s.equals("static"))
                fieldIsStatic = true;
            else
            if(s.equals("constructor"))
                fieldIsConstructor = true;
        fieldConstructors = getConstructors();
        fieldMethods = getMethods(amethod);
        if(Trc.ON)
            Trc.exit(deep());
    }

    private WSIFOperation_Spring(Port port, WSIFPort_Spring wsifport_spring, BindingOperation bindingoperation, SpringOperation springoperation, String as[], String as1[], Map map, 
            Method amethod[], Constructor aconstructor[], String s, boolean flag, boolean flag1, Map map1)
    {
        fieldInParameterNames = null;
        fieldOutParameterNames = null;
        fieldFaultMessageInfos = null;
        fieldMethods = null;
        fieldConstructors = null;
        fieldOutputMessageName = null;
        fieldIsStatic = false;
        fieldIsConstructor = false;
        fieldTypeMaps = null;
        Trc.entry(this, port, wsifport_spring, bindingoperation, springoperation, as, as1, map, amethod, aconstructor, s, new Boolean(flag), new Boolean(flag1), map1);
        fieldPortModel = port;
        fieldPort = wsifport_spring;
        fieldBindingOperationModel = bindingoperation;
        fieldSpringOperationModel = springoperation;
        fieldInParameterNames = as;
        fieldOutParameterNames = as1;
        fieldFaultMessageInfos = map;
        fieldMethods = amethod;
        fieldConstructors = aconstructor;
        fieldOutputMessageName = s;
        fieldIsStatic = flag;
        fieldIsConstructor = flag1;
        fieldTypeMaps = map1;
        if(Trc.ON)
            Trc.exit(deep());
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

    public WSIFOperation_Spring copy()
        throws WSIFException
    {
        Trc.entry(this);
        WSIFOperation_Spring wsifoperation_spring = new WSIFOperation_Spring(fieldPortModel, fieldPort, fieldBindingOperationModel, fieldSpringOperationModel, fieldInParameterNames, fieldOutParameterNames, fieldFaultMessageInfos, fieldMethods, fieldConstructors, fieldOutputMessageName, fieldIsStatic, fieldIsConstructor, fieldTypeMaps);
        Trc.exit(wsifoperation_spring);
        return wsifoperation_spring;
    }

    public String deep()
    {
        String s = "";
        try
        {
            s = new String(super.toString() + ":\n");
            s = s + "portModel:" + Trc.brief(fieldPortModel);
            s = s + " wsifPort_Spring:" + fieldPort;
            s = s + " bindingOperationModel:" + Trc.brief(fieldBindingOperationModel);
            s = s + " SpringOperation:" + fieldSpringOperationModel;
            s = s + Trc.brief("inParameterNames", fieldInParameterNames);
            s = s + Trc.brief("outParameterNames", fieldOutParameterNames);
            if(fieldFaultMessageInfos == null)
            {
                s = s + " faultMessageInfos:null";
            } else
            {
                Iterator iterator = fieldFaultMessageInfos.keySet().iterator();
                for(int i = 0; iterator.hasNext(); i++)
                {
                    String s1 = (String)iterator.next();
                    s = s + " faultMessageInfos[" + i + "]:" + s1 + " " + fieldFaultMessageInfos.get(s1);
                }

            }
            s = s + Trc.brief("methods", fieldMethods);
            s = s + Trc.brief("constructors", fieldConstructors);
            s = s + " outputMessageName:" + fieldOutputMessageName;
            s = s + " isStatic:" + fieldIsStatic;
            s = s + " isConstructor:" + fieldIsConstructor;
            if(fieldTypeMaps == null)
            {
                s = s + " faultTypeMaps:null";
            } else
            {
                Iterator iterator1 = fieldTypeMaps.keySet().iterator();
                for(int j = 0; iterator1.hasNext(); j++)
                {
                    QName qname = (QName)iterator1.next();
                    s = s + " typeMaps[" + j + "]:" + qname + " " + fieldTypeMaps.get(qname);
                }

            }
        }
        catch(Exception exception)
        {
            Trc.exceptionInTrace(exception);
        }
        return s;
    }

    public void executeInputOnlyOperation(WSIFMessage wsifmessage)
        throws WSIFException
    {
        Trc.entry(this, wsifmessage);
        close();
        try
        {
            Object obj = null;
            if(fieldIsConstructor)
            {
                if(fieldConstructors.length <= 0)
                    throw new WSIFException("No constructors found that match the parts specified");
            } else
            if(fieldMethods.length <= 0)
                throw new WSIFException("No methods named '" + fieldSpringOperationModel.getMethodName() + "' found that match the parts specified");
            Object aobj[] = null;
            Object obj4 = null;
            if(fieldInParameterNames != null && fieldInParameterNames.length > 0)
            {
                aobj = new Object[fieldInParameterNames.length];
                for(int i = 0; i < fieldInParameterNames.length; i++)
                    try
                    {
                        Object obj5 = wsifmessage.getObjectPart(fieldInParameterNames[i]);
                        aobj[i] = obj5;
                    }
                    catch(WSIFException wsifexception)
                    {
                        Trc.exception(wsifexception);
                        aobj[i] = null;
                    }

            }
            boolean flag = false;
            if(fieldIsConstructor)
            {
                for(int j = 0; j < fieldConstructors.length;)
                    try
                    {
                        Object aobj1[] = getCompatibleArguments(fieldConstructors[j].getParameterTypes(), aobj);
                        if(aobj1 != null)
                        {
                            Trc.event(this, "Invoking constructor ", fieldConstructors[j], " with arguments ", ((Object) (aobj1)));
                            Object obj1 = fieldConstructors[j].newInstance(aobj1);
                            Trc.event(this, "Returned from constructor, result is ", obj1);
                            fieldPort.setObjectReference(obj1);
                            flag = true;
                        }
                        break;
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        Trc.ignoredException(illegalargumentexception);
                        j++;
                    }

                if(!flag)
                    throw new WSIFException("Failed to call constructor for object in Spring operation");
            } else
            if(fieldIsStatic)
            {
                for(int k = 0; k < fieldMethods.length;)
                    try
                    {
                        Object aobj2[] = getCompatibleArguments(fieldMethods[k].getParameterTypes(), aobj);
                        if(aobj2 != null)
                        {
                            Trc.event(this, "Invoking method ", fieldMethods[k], " with arguments ", ((Object) (aobj2)));
                            Object obj2 = fieldMethods[k].invoke(null, aobj2);
                            Trc.event(this, "Returned from method, result is ", obj2);
                            flag = true;
                        }
                        break;
                    }
                    catch(IllegalArgumentException illegalargumentexception1)
                    {
                        Trc.ignoredException(illegalargumentexception1);
                        k++;
                    }

                if(!flag)
                    throw new WSIFException("Failed to invoke method '" + fieldSpringOperationModel.getMethodName() + "'");
            } else
            {
                for(int l = 0; l < fieldMethods.length;)
                    try
                    {
                        Object aobj3[] = getCompatibleArguments(fieldMethods[l].getParameterTypes(), aobj);
                        if(aobj3 != null)
                        {
                            Object obj6 = fieldPort.getObjectReference();
                            Trc.event(this, "Invoking object ", obj6, " method ", fieldMethods[l], " with arguments ", ((Object) (aobj3)));
                            Object obj3 = fieldMethods[l].invoke(obj6, aobj3);
                            Trc.event(this, "Returned from method, result is ", obj3);
                            flag = true;
                        }
                        break;
                    }
                    catch(IllegalArgumentException illegalargumentexception2)
                    {
                        Trc.ignoredException(illegalargumentexception2);
                        l++;
                    }

                if(!flag)
                    throw new WSIFException("Failed to invoke method '" + fieldSpringOperationModel.getMethodName() + "'");
            }
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Trc.exception(invocationtargetexception);
            MessageLogger.log("WSIF.0005E", "Spring", fieldSpringOperationModel.getMethodName());
            throw new WSIFException(String.valueOf(this) + " : Invocation of '" + fieldSpringOperationModel.getMethodName() + "' failed.", invocationtargetexception);
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            MessageLogger.log("WSIF.0005E", "Spring", fieldSpringOperationModel.getMethodName());
            throw new WSIFException(String.valueOf(this) + " : Could not invoke '" + fieldSpringOperationModel.getMethodName() + "'", exception);
        }
        Trc.exit();
    }

    public boolean executeRequestResponseOperation(WSIFMessage wsifmessage, WSIFMessage wsifmessage1, WSIFMessage wsifmessage2)
        throws WSIFException
    {
        Trc.entry(this, wsifmessage, wsifmessage1, wsifmessage2);
        close();
        boolean flag = true;
        boolean flag1 = false;
        Method method = null;
        try
        {
            Object obj = null;
            if(fieldIsConstructor)
            {
                if(fieldConstructors.length <= 0)
                    throw new WSIFException("No constructor found that match the parts specified");
            } else
            if(fieldMethods.length <= 0)
                throw new WSIFException("No method named '" + fieldSpringOperationModel.getMethodName() + "' found that match the parts specified");
            Object aobj[] = null;
            Object obj1 = null;
            if(fieldInParameterNames != null && fieldInParameterNames.length > 0)
            {
                aobj = new Object[fieldInParameterNames.length];
                for(int i = 0; i < fieldInParameterNames.length; i++)
                    try
                    {
                        Object obj2 = wsifmessage.getObjectPart(fieldInParameterNames[i]);
                        aobj[i] = obj2;
                    }
                    catch(WSIFException wsifexception)
                    {
                        Trc.exception(wsifexception);
                        aobj[i] = null;
                        if(fieldOutParameterNames.length > 0)
                        {
                            Object obj4 = null;
                            for(int l1 = 0; l1 < fieldOutParameterNames.length; l1++)
                            {
                                String s3 = fieldOutParameterNames[l1];
                                if(s3 != null && s3.equals(fieldInParameterNames[i]))
                                {
                                    aobj[i] = fieldMethods[0].getParameterTypes()[i].newInstance();
                                    flag1 = true;
                                }
                            }

                        }
                    }

            }
            boolean flag2 = false;
            if(fieldIsConstructor)
            {
                for(int j = 0; j < fieldConstructors.length;)
                    try
                    {
                        Object aobj1[] = getCompatibleArguments(fieldConstructors[j].getParameterTypes(), aobj);
                        if(aobj1 != null)
                        {
                            Trc.event(this, "Invoking constructor ", fieldConstructors[j], " with arguments ", ((Object) (aobj1)));
                            obj = fieldConstructors[j].newInstance(aobj1);
                            Trc.event(this, "Returned from constructor, result is ", obj);
                            fieldPort.setObjectReference(obj);
                            flag2 = true;
                        }
                        break;
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        Trc.ignoredException(illegalargumentexception);
                        j++;
                    }

                if(!flag2)
                    throw new WSIFException("Failed to call constructor for object in Spring operation");
            } else
            {
                if(fieldIsStatic)
                {
                    for(int k = 0; k < fieldMethods.length;)
                    {
                        if(flag1)
                        {
                            for(int i1 = 0; i1 < fieldInParameterNames.length; i1++)
                            {
                                Object obj5 = null;
                                for(int j2 = 0; j2 < fieldOutParameterNames.length; j2++)
                                {
                                    String s4 = fieldOutParameterNames[j2];
                                    if(s4 != null && s4.equals(fieldInParameterNames[i1]))
                                        aobj[i1] = fieldMethods[k].getParameterTypes()[i1].newInstance();
                                }

                            }

                        }
                        try
                        {
                            Object aobj2[] = getCompatibleArguments(fieldMethods[k].getParameterTypes(), aobj);
                            if(aobj2 != null)
                            {
                                Trc.event(this, "Invoking method ", fieldMethods[k], " with arguments ", ((Object) (aobj2)));
                                obj = fieldMethods[k].invoke(null, aobj2);
                                Trc.event(this, "Returned from method, result is ", obj);
                                method = fieldMethods[k];
                                flag2 = true;
                            }
                            break;
                        }
                        catch(IllegalArgumentException illegalargumentexception1)
                        {
                            Trc.ignoredException(illegalargumentexception1);
                            k++;
                        }
                    }

                    if(!flag2)
                        throw new WSIFException("Failed to invoke method '" + fieldSpringOperationModel.getMethodName() + "'");
                } else
                {
                    for(int l = 0; l < fieldMethods.length;)
                    {
                        if(flag1)
                        {
                            for(int j1 = 0; j1 < fieldInParameterNames.length; j1++)
                            {
                                Object obj6 = null;
                                for(int k2 = 0; k2 < fieldOutParameterNames.length; k2++)
                                {
                                    String s5 = fieldOutParameterNames[k2];
                                    if(s5 != null && s5.equals(fieldInParameterNames[j1]))
                                        aobj[j1] = fieldMethods[l].getParameterTypes()[j1].newInstance();
                                }

                            }

                        }
                        try
                        {
                            Object aobj3[] = getCompatibleArguments(fieldMethods[l].getParameterTypes(), aobj);
                            if(aobj3 != null)
                            {
                                Object obj7 = fieldPort.getObjectReference();
                                Trc.event(this, "Invoking object ", obj7, " method ", fieldMethods[l], " with arguments ", ((Object) (aobj3)));
                                obj = fieldMethods[l].invoke(obj7, aobj3);
                                Trc.event(this, "Returned from method, result is ", obj);
                                method = fieldMethods[l];
                                flag2 = true;
                            }
                            break;
                        }
                        catch(IllegalArgumentException illegalargumentexception2)
                        {
                            Trc.ignoredException(illegalargumentexception2);
                            l++;
                        }
                    }

                    if(!flag2)
                        throw new WSIFException("Failed to invoke method '" + fieldSpringOperationModel.getMethodName() + "'");
                }
                Object obj3 = null;
                if(fieldOutParameterNames != null && fieldOutParameterNames.length > 0)
                {
                    wsifmessage1.setName(getOutputMessageName());
                    String s1 = fieldOutParameterNames[0];
                    if(s1 != null)
                        wsifmessage1.setObjectPart(s1, getCompatibleReturn(method, obj));
                    if(aobj != null)
                    {
                        for(int k1 = 1; k1 < fieldOutParameterNames.length; k1++)
                        {
                            String s2 = fieldOutParameterNames[k1];
                            if(s2 != null)
                                try
                                {
                                    for(int i2 = 0; i2 < fieldInParameterNames.length; i2++)
                                    {
                                        if(!s2.equals(fieldInParameterNames[i2]))
                                            continue;
                                        wsifmessage1.setObjectPart(s2, aobj[i2]);
                                        break;
                                    }

                                }
                                catch(WSIFException wsifexception1)
                                {
                                    Trc.ignoredException(wsifexception1);
                                }
                        }

                    }
                }
            }
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Trc.exception(invocationtargetexception);
            Throwable throwable = invocationtargetexception.getTargetException();
            String s = throwable.getClass().getName();
            Map map = getFaultMessageInfos();
            FaultMessageInfo faultmessageinfo = (FaultMessageInfo)map.get(s);
            if(faultmessageinfo != null && faultmessageinfo.fieldPartName != null)
            {
                Throwable throwable1 = throwable;
                wsifmessage2.setObjectPart(faultmessageinfo.fieldPartName, throwable1);
                wsifmessage2.setName(faultmessageinfo.fieldMessageName);
                if(faultmessageinfo.fieldMessageName != null)
                {
                    Fault fault = fieldBindingOperationModel.getOperation().getFault(faultmessageinfo.fieldMessageName);
                    if(fault != null)
                        wsifmessage2.setMessageDefinition(fault.getMessage());
                }
                flag = false;
            } else
            {
                Class class1 = throwable.getClass();
                Object obj8 = null;
                Iterator iterator = map.values().iterator();
                boolean flag3 = false;
                while(iterator.hasNext()) 
                {
                    FaultMessageInfo faultmessageinfo1 = (FaultMessageInfo)iterator.next();
                    try
                    {
                        Class class2 = Class.forName(faultmessageinfo1.fieldFormatType, true, Thread.currentThread().getContextClassLoader());
                        if(class2.isAssignableFrom(class1))
                        {
                            flag3 = true;
                            Throwable throwable2 = throwable;
                            wsifmessage2.setObjectPart(faultmessageinfo1.fieldPartName, throwable2);
                            wsifmessage2.setName(faultmessageinfo1.fieldMessageName);
                            if(faultmessageinfo1.fieldMessageName != null)
                            {
                                Fault fault1 = fieldBindingOperationModel.getOperation().getFault(faultmessageinfo1.fieldMessageName);
                                if(fault1 != null)
                                    wsifmessage2.setMessageDefinition(fault1.getMessage());
                            }
                            flag = false;
                        }
                    }
                    catch(Exception exception1)
                    {
                        Trc.ignoredException(exception1);
                    }
                }
                if(!flag3)
                    throw new WSIFException("Operation failed!", throwable);
            }
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            MessageLogger.log("WSIF.0005E", "Spring", fieldSpringOperationModel.getMethodName());
            throw new WSIFException(String.valueOf(this) + " : Could not invoke '" + fieldSpringOperationModel.getMethodName() + "'", exception);
        }
        Trc.exit(flag);
        return flag;
    }

    protected static Class getClassForName(String s)
        throws WSIFException
    {
        Trc.entry(null, s);
        Class class1 = null;
        if(s == null)
            throw new WSIFException("Error in getClassForName(): No class name specified!");
        try
        {
            if(s.lastIndexOf('.') == -1)
            {
                if(s.equals("char"))
                    class1 = Character.TYPE;
                else
                if(s.equals("boolean"))
                    class1 = Boolean.TYPE;
                else
                if(s.equals("byte"))
                    class1 = Byte.TYPE;
                else
                if(s.equals("short"))
                    class1 = Short.TYPE;
                else
                if(s.equals("int"))
                    class1 = Integer.TYPE;
                else
                if(s.equals("long"))
                    class1 = Long.TYPE;
                else
                if(s.equals("float"))
                    class1 = Float.TYPE;
                else
                if(s.equals("double"))
                    class1 = Double.TYPE;
                else
                    class1 = Class.forName(s, true, Thread.currentThread().getContextClassLoader());
            } else
            {
                class1 = Class.forName(s, true, Thread.currentThread().getContextClassLoader());
            }
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            Trc.exception(classnotfoundexception);
            throw new WSIFException("Could not instantiate class '" + s + "'", classnotfoundexception);
        }
        Trc.exit(class1);
        return class1;
    }

    protected Object[] getCompatibleArguments(Class aclass[], Object aobj[])
    {
        Trc.entry(this, aclass, ((Object) (aobj)));
        if(aobj == null || aclass == null)
        {
            Object aobj1[] = new Object[0];
            return aobj1;
        }
        Object aobj2[] = new Object[aobj.length];
        for(int i = 0; i < aclass.length; i++)
            if(aobj[i] == null)
            {
                aobj2[i] = ProviderUtils.getDefaultObject(aclass[i]);
            } else
            {
                Object obj = getCompatibleObject(aclass[i], aobj[i]);
                if(obj == null)
                    return null;
                aobj2[i] = obj;
            }

        Trc.exit(((Object) (aobj2)));
        return aobj2;
    }

    protected Object getCompatibleObject(Class class1, Object obj)
    {
        Trc.entry(this, class1, obj);
        if(class1.getName().equals(obj.getClass().getName()))
            return obj;
        if((class1.equals(java.lang.Character.class) || class1.equals(Character.TYPE)) && obj.getClass().equals(java.lang.String.class))
        {
            Character character = ProviderUtils.stringToCharacter((String)obj);
            if(character == null)
            {
                Trc.exit(null);
                return null;
            } else
            {
                Trc.exit(character);
                return character;
            }
        }
        if(class1.isArray() && obj.getClass().isArray())
        {
            Class class2 = class1.getComponentType();
            Class class3 = obj.getClass().getComponentType();
            for(; class2.isArray(); class2 = class2.getComponentType());
            for(; class3.isArray(); class3 = class3.getComponentType());
            if(class3.equals(java.lang.String.class) && class2.equals(Character.TYPE))
            {
                try
                {
                    Object obj1 = ProviderUtils.stringArrayToCharArray(obj);
                    Trc.exit(obj1);
                    return obj1;
                }
                catch(Exception exception)
                {
                    Trc.ignoredException(exception);
                }
                Trc.exit(null);
                return null;
            }
            if(class3.equals(java.lang.String.class) && class2.equals(java.lang.Character.class))
            {
                try
                {
                    Object obj2 = ProviderUtils.stringArrayToCharacterArray(obj);
                    Trc.exit(obj2);
                    return obj2;
                }
                catch(Exception exception1)
                {
                    Trc.ignoredException(exception1);
                }
                Trc.exit(null);
                return null;
            }
            if(class3.equals(java.lang.Character.class) && class2.equals(java.lang.String.class))
            {
                try
                {
                    Object obj3 = ProviderUtils.characterArrayToStringArray(obj);
                    Trc.exit(obj3);
                    return obj3;
                }
                catch(Exception exception2)
                {
                    Trc.ignoredException(exception2);
                }
                Trc.exit(null);
                return null;
            }
            if(class3.equals(Character.TYPE) && class2.equals(java.lang.String.class))
            {
                try
                {
                    Object obj4 = ProviderUtils.charArrayToStringArray(obj);
                    Trc.exit(obj4);
                    return obj4;
                }
                catch(Exception exception3)
                {
                    Trc.ignoredException(exception3);
                }
                Trc.exit(null);
                return null;
            }
        }
        if(class1.equals(java.lang.String.class) && obj.getClass().equals(java.lang.Character.class))
        {
            Trc.exit(obj.toString());
            return obj.toString();
        } else
        {
            Trc.exit(obj);
            return obj;
        }
    }

    protected Object getCompatibleReturn(Method method, Object obj)
    {
        Trc.entry(this, method, obj);
        Object obj1 = null;
        Class class1 = method.getReturnType();
        Class class2 = null;
        int i = 0;
        if(class1.isArray())
        {
            class2 = class1.getComponentType();
            for(i++; class2.isArray(); i++)
                class2 = class2.getComponentType();

        }
        if(obj instanceof Character)
            obj1 = getCompatibleObject(java.lang.String.class, obj);
        else
        if(class2 != null && (class2.equals(java.lang.Character.class) || class2.equals(Character.TYPE)))
        {
            String s = "[Ljava.lang.String;";
            for(int j = 1; j < i; j++)
                s = "[" + s;

            try
            {
                Class class3 = Class.forName(s, true, Thread.currentThread().getContextClassLoader());
                obj1 = getCompatibleObject(class3, obj);
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                Trc.ignoredException(classnotfoundexception);
                obj1 = obj;
            }
        } else
        {
            obj1 = obj;
        }
        Trc.exit(obj1);
        return obj1;
    }

    protected Constructor[] getConstructors()
        throws WSIFException
    {
        Trc.entry(this);
        Constructor aconstructor1[] = fieldPort.getObjectReference().getClass().getConstructors();
        Object aobj[] = getMethodArgumentClasses();
        Vector vector = new Vector();
        for(int i = 0; i < aconstructor1.length; i++)
        {
            Class aclass[] = aconstructor1[i].getParameterTypes();
            if(aclass.length == aobj.length)
            {
                boolean flag = true;
                for(int k = 0; k < aclass.length; k++)
                {
                    Object obj = aobj[k];
                    if(obj instanceof Vector)
                    {
                        Vector vector1 = (Vector)obj;
                        boolean flag1 = false;
                        for(int l = 0; l < vector1.size(); l++)
                        {
                            Class class1 = (Class)vector1.get(l);
                            if(class1.getName().equals(aclass[k].getName()))
                            {
                                flag1 = true;
                                break;
                            }
                            if(!aclass[k].isAssignableFrom(class1))
                                continue;
                            flag1 = true;
                            break;
                        }

                        if(flag1)
                            continue;
                        flag = false;
                        break;
                    }
                    if(((Class)obj).getName().equals(aclass[k].getName()) || aclass[k].isAssignableFrom((Class)obj))
                        continue;
                    flag = false;
                    break;
                }

                if(flag)
                    vector.addElement(aconstructor1[i]);
            }
        }

        Constructor aconstructor[] = new Constructor[vector.size()];
        for(int j = 0; j < aconstructor.length; j++)
            aconstructor[j] = (Constructor)vector.get(j);

        Trc.exit(aconstructor);
        return aconstructor;
    }

    protected Map getFaultMessageInfos()
        throws WSIFException
    {
        Trc.entry(this);
        Operation operation = null;
        try
        {
            operation = getOperation();
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            throw new WSIFException("Failed to get Operation", exception);
        }
        if(fieldFaultMessageInfos == null)
            fieldFaultMessageInfos = new HashMap();
        Object obj = null;
        Map map = fieldBindingOperationModel.getBindingFaults();
        Object obj1 = null;
        for(Iterator iterator = map.values().iterator(); iterator.hasNext();)
        {
            BindingFault bindingfault = (BindingFault)iterator.next();
            String s = bindingfault.getName();
            if(s == null)
                throw new WSIFException("Fault name not found in binding");
            Map map1 = operation.getFault(s).getMessage().getParts();
            if(map1.size() >= 1)
            {
                Part part = (Part)map1.values().iterator().next();
                QName qname = part.getTypeName();
                if(qname == null)
                    qname = part.getElementName();
                Object obj2 = fieldTypeMaps.get(qname);
                if(obj2 == null)
                    throw new WSIFException("formatType for typeName '" + part.getName() + "' not found in document");
                if(obj2 instanceof Vector)
                {
                    Vector vector = (Vector)obj2;
                    String s2;
                    for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); fieldFaultMessageInfos.put(s2, new FaultMessageInfo(s, part.getName(), s2)))
                        s2 = (String)enumeration.nextElement();

                } else
                {
                    String s1 = (String)obj2;
                    fieldFaultMessageInfos.put(s1, new FaultMessageInfo(s, part.getName(), s1));
                }
            }
        }

        Trc.exit(fieldFaultMessageInfos);
        return fieldFaultMessageInfos;
    }

    protected Object[] getMethodArgumentClasses()
        throws WSIFException
    {
        Trc.entry(this);
        Object aobj[] = null;
        try
        {
            Operation operation = getOperation();
            Object obj = null;
            obj = fieldSpringOperationModel.getParameterOrder();
            if(obj == null)
                obj = operation.getParameterOrdering();
            if(obj == null)
            {
                List list = operation.getInput().getMessage().getOrderedParts(null);
                obj = new Vector();
                Part part;
                for(Iterator iterator = list.iterator(); iterator.hasNext(); ((List) (obj)).add(part.getName()))
                    part = (Part)iterator.next();

            }
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist2 = new ArrayList();
            for(Iterator iterator1 = ((List) (obj)).iterator(); iterator1.hasNext();)
            {
                String s = (String)iterator1.next();
                Part part1 = operation.getInput().getMessage().getPart(s);
                if(part1 == null)
                    part1 = operation.getOutput().getMessage().getPart(s);
                if(part1 == null)
                    throw new Exception("Part '" + s + "' from parameterOrder not found in input or output message");
                arraylist.add(part1.getName());
                QName qname = part1.getTypeName();
                if(qname == null)
                    qname = part1.getElementName();
                Object obj1 = fieldTypeMaps.get(qname);
                if(obj1 == null)
                    throw new WSIFException("Could not map type " + qname + " to a java type. Part name was " + part1.getName() != null ? part1.getName() : "<null>");
                if(obj1 instanceof Vector)
                {
                    Vector vector = (Vector)obj1;
                    Vector vector1 = new Vector();
                    String s3;
                    for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); vector1.addElement(getClassForName(s3)))
                        s3 = (String)enumeration.nextElement();

                    arraylist2.add(vector1);
                } else
                {
                    arraylist2.add(getClassForName((String)obj1));
                }
            }

            aobj = arraylist2.toArray();
            fieldInParameterNames = new String[arraylist.size()];
            for(int i = 0; i < arraylist.size(); i++)
                fieldInParameterNames[i] = (String)arraylist.get(i);

            if(operation.getStyle().equals(OperationType.REQUEST_RESPONSE))
            {
                ArrayList arraylist1 = new ArrayList();
                String s1 = fieldSpringOperationModel.getReturnPart();
                for(Iterator iterator2 = operation.getOutput().getMessage().getOrderedParts(null).iterator(); iterator2.hasNext();)
                {
                    Part part2 = (Part)iterator2.next();
                    String s2 = part2.getName();
                    if(s2 != null && s1 != null && s2.equals(s1))
                        arraylist1.add(0, s2);
                    else
                        arraylist1.add(part2.getName());
                }

                fieldOutParameterNames = new String[arraylist1.size()];
                for(int j = 0; j < arraylist1.size(); j++)
                    fieldOutParameterNames[j] = (String)arraylist1.get(j);

            } else
            {
                fieldOutParameterNames = new String[0];
            }
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            throw new WSIFException("Error while determining signature of method " + fieldSpringOperationModel.getMethodName() + " : The meta information is not consistent.", exception);
        }
        Trc.exit(((Object) (aobj)));
        return aobj;
    }

    protected Object getMethodReturnClass()
        throws WSIFException
    {
        Trc.entry(this);
        Object obj = null;
        try
        {
            String s = fieldSpringOperationModel.getReturnPart();
            if(s != null)
            {
                Part part = getOperation().getOutput().getMessage().getPart(s);
                if(part != null)
                {
                    QName qname = part.getTypeName();
                    if(qname == null)
                        qname = part.getElementName();
                    Object obj1 = fieldTypeMaps.get(qname);
                    if(obj1 == null)
                        throw new WSIFException("Could not map type " + qname + " to a java type. Part name was " + part.getName() != null ? part.getName() : "<null>");
                    if(obj1 instanceof Vector)
                    {
                        Vector vector = (Vector)obj1;
                        Vector vector1 = new Vector();
                        String s1;
                        for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); vector1.addElement(getClassForName(s1)))
                            s1 = (String)enumeration.nextElement();

                        obj = vector1;
                    } else
                    {
                        obj = getClassForName((String)obj1);
                    }
                } else
                {
                    throw new Exception("returnPart '" + s + "' was not in the output message");
                }
            }
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            throw new WSIFException("Error while determining return class of method " + fieldSpringOperationModel.getMethodName() + " : The meta information is not consistent.", exception);
        }
        Trc.exit(obj);
        return obj;
    }

    protected Method[] getMethods(Method amethod[])
        throws WSIFException
    {
        Trc.entry(this, amethod);
        try
        {
            if(!fieldIsConstructor)
            {
                Object aobj[] = getMethodArgumentClasses();
                Object obj = getMethodReturnClass();
                Vector vector = new Vector();
                for(int i = 0; i < amethod.length; i++)
                {
                    if(!amethod[i].getName().equals(fieldSpringOperationModel.getMethodName()))
                        continue;
                    Class aclass[] = amethod[i].getParameterTypes();
                    if(aclass.length != aobj.length)
                        continue;
                    Class class1 = amethod[i].getReturnType();
                    if(obj != null && (obj instanceof Vector))
                    {
                        Vector vector1 = (Vector)obj;
                        boolean flag1 = false;
                        for(int l = 0; l < vector1.size(); l++)
                        {
                            Class class2 = (Class)vector1.get(l);
                            if(class2.getName().equals(class1.getName()))
                            {
                                flag1 = true;
                                break;
                            }
                            if(!class2.isAssignableFrom(class1))
                                continue;
                            flag1 = true;
                            break;
                        }

                        if(!flag1)
                            continue;
                    } else
                    if(class1 != null && obj != null && !((Class)obj).getName().equals(class1.getName()) && !((Class)obj).isAssignableFrom(class1))
                        continue;
                    boolean flag = true;
                    for(int k = 0; k < aclass.length; k++)
                    {
                        Object obj1 = aobj[k];
                        if(obj1 instanceof Vector)
                        {
                            Vector vector2 = (Vector)obj1;
                            boolean flag2 = false;
                            for(int i1 = 0; i1 < vector2.size(); i1++)
                            {
                                Class class3 = (Class)vector2.get(i1);
                                if(class3.getName().equals(aclass[k].getName()))
                                {
                                    flag2 = true;
                                    break;
                                }
                                if(!aclass[k].isAssignableFrom(class3))
                                    continue;
                                flag2 = true;
                                break;
                            }

                            if(flag2)
                                continue;
                            flag = false;
                            break;
                        }
                        if(((Class)obj1).getName().equals(aclass[k].getName()) || aclass[k].isAssignableFrom((Class)obj1))
                            continue;
                        flag = false;
                        break;
                    }

                    if(flag)
                        vector.addElement(amethod[i]);
                }

                Method amethod1[] = new Method[vector.size()];
                for(int j = 0; j < amethod1.length; j++)
                    amethod1[j] = (Method)vector.get(j);

                Trc.exit(amethod1);
                return amethod1;
            } else
            {
                Method amethod2[] = new Method[0];
                Trc.exit(amethod2);
                return amethod2;
            }
        }
        catch(WSIFException wsifexception)
        {
            Trc.exception(wsifexception);
            throw wsifexception;
        }
        catch(Exception exception)
        {
            Trc.exception(exception);
            throw new WSIFException("Error while resolving meta information of method " + fieldSpringOperationModel.getMethodName() + " : The meta information is not consistent.", exception);
        }
    }

    protected Operation getOperation()
        throws Exception
    {
        Trc.entry(this);
        Operation operation = null;
        String s = null;
        if(fieldBindingOperationModel.getBindingInput() != null)
            s = fieldBindingOperationModel.getBindingInput().getName();
        String s1 = null;
        if(fieldBindingOperationModel.getBindingOutput() != null)
            s1 = fieldBindingOperationModel.getBindingOutput().getName();
        operation = fieldPortModel.getBinding().getPortType().getOperation(fieldBindingOperationModel.getName(), s, s1);
        Trc.exit(operation);
        return operation;
    }

    protected String getOutputMessageName()
        throws WSIFException
    {
        Trc.entry(this);
        if(fieldOutputMessageName == null)
        {
            BindingOutput bindingoutput = fieldBindingOperationModel.getBindingOutput();
            if(bindingoutput != null)
                fieldOutputMessageName = bindingoutput.getName();
        }
        Trc.exit(fieldOutputMessageName);
        return fieldOutputMessageName;
    }

    public WSIFPort getWSIFPort()
    {
        Trc.entry(this);
        Trc.exit(fieldPort);
        return fieldPort;
    }

    private static final long serialVersionUID = 1L;
    protected Port fieldPortModel;
    protected WSIFPort_Spring fieldPort;
    protected BindingOperation fieldBindingOperationModel;
    protected SpringOperation fieldSpringOperationModel;
    protected String fieldInParameterNames[];
    protected String fieldOutParameterNames[];
    protected Map fieldFaultMessageInfos;
    protected Method fieldMethods[];
    protected Constructor fieldConstructors[];
    protected String fieldOutputMessageName;
    protected boolean fieldIsStatic;
    protected boolean fieldIsConstructor;
    protected Map fieldTypeMaps;
}