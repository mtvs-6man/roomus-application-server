package com.sixman.roomus.common.repository;

import com.sixman.roomus.common.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
