package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import ru.skypro.lessons.springboot.weblibrary.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ReportRepositoryImpl implements ReportRepository {

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.DTO." +
            "ReportDTO (p.name, count (e), MAX(e.salary), MIN(e.salary), AVG(e.salary))" +
            "FROM Employee e join fetch Position p WHERE e.position = p GROUP BY p.name")
    @Override
    public List<ReportDTO> getReport() {
        return null;
    }

    @Override
    public <S extends Report> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Report> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Report> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Report> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Report> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Report getOne(Integer integer) {
        return null;
    }

    @Override
    public Report getById(Integer integer) {
        return null;
    }

    @Override
    public Report getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Report> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Report> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Report> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Report> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Report> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Report> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Report, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<Report> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public List<Report> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Report> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Report entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Report> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
