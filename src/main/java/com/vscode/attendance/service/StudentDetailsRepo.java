package com.vscode.attendance.service;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vscode.common.repo.DefaultRepo;
import com.vscode.entity.attendance.StudentDetailsBean;

/**
 * @author Utkarsh Bhardwaj 
 */

public interface StudentDetailsRepo extends DefaultRepo<StudentDetailsBean, BigInteger> {

}
