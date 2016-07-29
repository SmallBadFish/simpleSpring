package com.yin.practice.simplespring.beans.factory.support;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanIsNotAFactoryException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import com.yin.practice.simplespring.beans.BeansException;
import com.yin.practice.simplespring.beans.factory.FactoryBean;
import com.yin.practice.simplespring.beans.factory.ListableBeanFactory;
import com.yin.practice.simplespring.beans.factory.SmartFactoryBean;

/**
 * 该类是ListabelBeanFactory的静态实现类,只允许编程化得注册单例的bean,不支持原型类型的bean
 *
 */
public class StaticListableBeanFactory implements ListableBeanFactory {

	/* Map from bean name to bean instance */
	private final Map<String, Object> beans = new HashMap<String, Object>();

	/**
	 * 添加一个单例bean
	 * 
	 * @param name
	 * @param bean
	 */
	public void addBean(String name, Object bean) {
		this.beans.put(name, bean);
	}

	public Object getBean(String name) throws BeansException {
		String beanName = BeanFactoryUtils.transformedBeanName(name);
		Object bean = this.beans.get(beanName);
		if (bean == null) {
			throw new NoSuchBeanDefinitionException(beanName,
					"Defined beans are[" + StringUtils.collectionToCommaDelimitedString(this.beans.keySet()) + "]");
		}
		if (BeanFactoryUtils.isFactoryDereference(name) && !(bean instanceof FactoryBean)) {
			throw new BeanIsNotAFactoryException(beanName, bean.getClass());
		}
		if (bean instanceof FactoryBean && !BeanFactoryUtils.isFactoryDereference(name)) {
			try {
				return ((FactoryBean<?>) bean).getObject();
			} catch (Exception ex) {
				throw new BeanCreationException(beanName, "FactoryBean threw exception on Object creation", ex);
			}
		} else {
			return bean;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		Object bean = getBean(name);
		if (requiredType != null && !requiredType.isAssignableFrom(bean.getClass())) {
			throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
		}
		return (T) bean;
	}

	public <T> T getBean(Class<T> requiredType) throws BeansException {
		String[] beanNames = getBeanNamesForType(requiredType);
		if (beanNames.length == 1) {
			return getBean(beanNames[0], requiredType);
		} else if (beanNames.length > 1) {
			throw new NoUniqueBeanDefinitionException(requiredType, beanNames);
		} else {
			throw new NoSuchBeanDefinitionException(requiredType);
		}
	}

	public Object getBean(String name, Object... args) throws BeansException {
		if (args != null) {
			throw new UnsupportedOperationException(
					"StaticListableBeanFactory does not support explicit bean creation arguments");
		}
		return getBean(name);
	}

	public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
		if (args != null) {
			throw new UnsupportedOperationException(
					"StaticListableBeanFactory does not support explicit bean creation arguments");
		}
		return getBean(requiredType);
	}

	public boolean containsBean(String name) {
		return this.beans.containsKey(name);
	}

	public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		Object bean = getBean(name);
		return (bean instanceof FactoryBean && ((FactoryBean<?>) bean).isSingleton());
	}

	public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
		Object bean = getBean(name);
		return ((bean instanceof SmartFactoryBean && ((SmartFactoryBean<?>) bean).isPrototype())
				|| (bean instanceof FactoryBean && !((FactoryBean<?>) bean).isSingleton()));
	}

	public boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException {
		Class<?> type = getType(name);
		return (targetType == null) || (type != null && targetType.isAssignableFrom(type));
	}

	public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		String beanName = BeanFactoryUtils.transformedBeanName(name);
		Object bean = this.beans.get(beanName);
		if (bean == null) {
			throw new NoSuchBeanDefinitionException(
					"Defined beans are [" + StringUtils.collectionToCommaDelimitedString(this.beans.keySet()) + "]");
		}
		if (bean instanceof FactoryBean && !BeanFactoryUtils.isFactoryDereference(name)) {
			return ((FactoryBean<?>) bean).getObjectType();
		}
		return bean.getClass();
	}

	public String[] getAliases(String name) {
		return new String[0];
	}

	// ---------------------------------------------------------------------
	// Implementation of ListableBeanFactory interface
	// ---------------------------------------------------------------------

	public boolean containsBeanDefinition(String beanName) {
		return this.beans.containsKey(beanName);
	}

	public int getBeanDefinitionCount() {
		return this.beans.size();
	}

	public String[] getBeanDefinitionNames() {
		return StringUtils.toStringArray(this.beans.keySet());
	}

	public String[] getBeanNamesForType(Class<?> type) {
		return getBeanNamesForType(type, true, true);
	}

	public String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean includeFactoryBeans) {
		boolean isFactoryType = (type != null && FactoryBean.class.isAssignableFrom(type));
		List<String> matches = new ArrayList<String>();
		for (String name : this.beans.keySet()) {
			Object beanInstance = this.beans.get(name);
			if (beanInstance instanceof FactoryBean && !isFactoryType) {
				if (includeFactoryBeans) {
					Class<?> objectType = ((FactoryBean<?>) beanInstance).getObjectType();
					if (objectType != null && (type == null || type.isAssignableFrom(objectType))) {
						matches.add(name);
					}
				}
			} else {
				if (type == null || type.isInstance(beanInstance)) {
					matches.add(name);
				}
			}
		}
		return StringUtils.toStringArray(matches);
	}

	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return getBeansOfType(type, true, true);
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean includeFactoryBeans)
			throws BeansException {
		boolean isFactoryType = (type != null && FactoryBean.class.isAssignableFrom(type));
		Map<String, T> matches = new HashMap<String, T>();

		for (Map.Entry<String, Object> entry : beans.entrySet()) {
			String beanName = entry.getKey();
			Object beanInstance = entry.getValue();
			if (beanInstance instanceof FactoryBean && !isFactoryType) {
				if (includeFactoryBeans) {
					FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
					Class<?> objectType = factory.getObjectType();
					if ((includeNonSingletons || factory.isSingleton()) && objectType != null
							&& (type == null || type.isAssignableFrom(objectType))) {
						matches.put(beanName, getBean(beanName, type));
					}
				}
			} else {
				if (type == null || type.isInstance(beanInstance)) {
					if (isFactoryType) {
						beanName = FACTORY_BEAN_PREFIX + beanName;
					}
					matches.put(beanName, (T) beanInstance);
				}
			}
			return matches;
		}
		return matches;
	}

	public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
		List<String> results = new ArrayList<String>();
		for (String beanName : this.beans.keySet()) {
			if (findAnnotationOnBean(beanName, annotationType) != null) {
				results.add(beanName);

			}
		}
		return results.toArray(new String[results.size()]);
	}

	public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
			throws BeansException {
		Map<String, Object> results = new LinkedHashMap<String, Object>();
		for (String beanName : this.beans.keySet()) {
			if (findAnnotationOnBean(beanName, annotationType) != null) {
				results.put(beanName, getBean(beanName));
			}
		}
		return results;
	}

	public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
			throws NoSuchBeanDefinitionException {
		return AnnotationUtils.findAnnotation(getType(beanName), annotationType);
	}
}
