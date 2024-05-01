package com.doancoso3.management.service

import com.doancoso3.management.entity.Notification
import com.doancoso3.management.repository.NotificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationService(@Autowired private val notificationRepository: NotificationRepository) {

    fun getAllNotifications(): List<Notification> {
        return notificationRepository.findAll()
    }

    fun createNotification(notificationInfo: Notification): Notification {
        return notificationRepository.save(notificationInfo)
    }

    fun getNotificationById(id: Long): Notification {
        return notificationRepository.findById(id)
            .orElseThrow { NoSuchElementException("Notification with id $id not found") }
    }

    fun updateNotification(id: Long, updatedNotification: Notification): Notification {
        val existingNotification = getNotificationById(id)
        existingNotification.teacherId = updatedNotification.teacherId
        existingNotification.studentId = updatedNotification.studentId
        existingNotification.content = updatedNotification.content
        existingNotification.sentDate = updatedNotification.sentDate
        existingNotification.status = updatedNotification.status
        return notificationRepository.save(existingNotification)
    }

    fun deleteNotification(id: Long) {
        notificationRepository.deleteById(id)
    }
}
