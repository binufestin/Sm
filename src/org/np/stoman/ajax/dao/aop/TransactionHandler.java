package org.np.stoman.ajax.dao.aop;

import static org.np.stoman.dao.support.HibernateSupport.getHibernateSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.np.stoman.ajax.interfaze.AjaxMarker;
import org.np.stoman.dao.conf.HibernateUtil;

public class TransactionHandler implements InvocationHandler {

	private static final Logger logger = Logger
			.getLogger(TransactionHandler.class);

	public TransactionHandler(AjaxMarker bo) {
		this.bo = bo;
	}

	private final AjaxMarker bo;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Session session = HibernateUtil.openSession();
		getHibernateSupport().setSession(session);
		Transaction transaction = session.beginTransaction();
		Object obj = null;
		try {
			obj = method.invoke(bo, args);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			logger.error("Transaction Rollbacked: " + e);
			// Since this TransactionHandler will be used only
			// for AJAX request and response, sending a customized
			// exception format.
			return "E:" + e.getMessage();
		} finally {
			session.close();
		}
		return obj;
	}
}
