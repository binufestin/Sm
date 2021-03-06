package org.np.stoman.dao.support;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class HibernateSupport {

	private static HibernateSupport hs;
	private final ThreadLocal<Session> session;

	private HibernateSupport() {
		session = new ThreadLocal<Session>();
	}

	public synchronized static HibernateSupport getHibernateSupport() {
		if (hs == null)
			hs = new HibernateSupport();
		return hs;
	}

	public <T> void save(T t) {
		session.get().saveOrUpdate(t);
	}

	public <T> T get(Class<T> t, int id) {
		return t.cast(session.get().get(t, id));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t) {
		return session.get().createCriteria(t).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		addRestrictions(criteria, criterions);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, List<Order> orders,
			List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		addRestrictions(criteria, criterions);
		addOrders(criteria, orders);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Criteria criteria, List<Criterion>... criterions) {
		addRestrictions(criteria, criterions);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Criteria criteria, List<Order> orders,
			List<Criterion>... criterions) {
		addRestrictions(criteria, criterions);
		addOrders(criteria, orders);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, int pageNo, int pageSize,
			List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		fetchControl(criteria, pageNo, pageSize);
		addRestrictions(criteria, criterions);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, int pageNo, int pageSize,
			List<Order> orders, List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		fetchControl(criteria, pageNo, pageSize);
		addRestrictions(criteria, criterions);
		addOrders(criteria, orders);
		return criteria.list();
	}

	private void addRestrictions(Criteria criteria, List<Criterion>[] criterions) {
		for (List<Criterion> lcriterion : criterions)
			for (Criterion criterion : lcriterion)
				criteria.add(criterion);
	}

	private void addOrders(Criteria criteria, List<Order> orders) {
		for (Order order : orders)
			criteria.addOrder(order);
	}

	private void fetchControl(Criteria criteria, int firstResult, int fetchSize) {
		criteria.setFirstResult((firstResult - 1) * fetchSize);
		criteria.setMaxResults(fetchSize);
	}

	public <T> void evict(T t) {
		session.get().evict(t);
	}

	public void flush() {
		session.get().flush();
	}

	public <T> void delete(T t) {
		session.get().delete(t);
	}

	public void setSession(Session session) {
		this.session.set(session);
	}

	public Session getSession() {
		return this.session.get();
	}
}
