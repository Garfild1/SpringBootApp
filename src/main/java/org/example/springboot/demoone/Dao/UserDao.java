package org.example.springboot.demoone.Dao;

import org.example.springboot.demoone.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository <User, Long> {
}
