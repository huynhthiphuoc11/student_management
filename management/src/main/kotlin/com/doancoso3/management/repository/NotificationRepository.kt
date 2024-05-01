package com.doancoso3.management.repository

import com.doancoso3.management.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository: JpaRepository<Notification, Long>