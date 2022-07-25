//package com.vscode.common.repo;
//
//import java.io.Serializable;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.core.support.RepositoryFactorySupport;
//
//public class DefaultRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
//		extends JpaRepositoryFactoryBean<R, T, I> {
//
//	public DefaultRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
//		super(repositoryInterface);
//	}
//
//	protected RepositoryFactorySupport createRepositoryFacory(EntityManager entityManager) {
//		return new DefaultRepositoryFactory(entityManager);
//	}
//
//	private static class DefaultRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
//		private EntityManager entityManager;
//
//		public DefaultRepositoryFactory(EntityManager entityManager) {
//			super(entityManager);
//
//			this.entityManager = entityManager;
//		}
//
//		protected Object getTragetRepository(JpaEntityInformation jpaEntityInfo) {
//			return new BaseDefaultRepo<T, I>((Class<T>) jpaEntityInfo.getJavaType(), entityManager);
//
//		}
//
//		protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
//			return BaseDefaultRepo.class;
//		}
//	}
//}