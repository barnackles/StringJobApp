package com.barnackles.StringJobApp.repository;

import com.barnackles.StringJobApp.model.GeneratedString;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class GeneratedStringRepository implements JpaRepository<GeneratedString, Long> {


    @Override
    public List<GeneratedString> findAll() {
        return null;
    }

    @Override
    public List<GeneratedString> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<GeneratedString> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<GeneratedString> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(GeneratedString entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends GeneratedString> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends GeneratedString> S save(S entity) {
        return null;
    }

    @Override
    public <S extends GeneratedString> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<GeneratedString> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends GeneratedString> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends GeneratedString> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<GeneratedString> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public GeneratedString getOne(Long aLong) {
        return null;
    }

    @Override
    public GeneratedString getById(Long aLong) {
        return null;
    }

    @Override
    public GeneratedString getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends GeneratedString> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends GeneratedString> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends GeneratedString> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends GeneratedString> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends GeneratedString> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends GeneratedString> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends GeneratedString, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
