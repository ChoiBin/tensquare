package com.tensquare.qas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qas.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 * jpq
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ? order by replytime desc " , nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ? order by reply desc " , nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ? reply = 0 order by createtime desc " , nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);

}
