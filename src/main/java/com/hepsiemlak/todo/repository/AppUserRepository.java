package com.hepsiemlak.todo.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.hepsiemlak.todo.domain.model.AppUser;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.DynamicProxyable;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

@Repository("appUserRepository")
@Collection("users")
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface AppUserRepository extends CouchbaseRepository<AppUser, String>, DynamicProxyable<AppUserRepository> {

    boolean existsAppUserByEmail(String email);

    AppUser getAppUserByEmail(String email);

}
