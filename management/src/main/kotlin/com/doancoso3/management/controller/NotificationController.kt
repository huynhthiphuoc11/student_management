package com.doancoso3.management.controller

import com.doancoso3.management.entity.Notification
import com.doancoso3.management.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notifications")
class NotificationController(@Autowired private val notificationService: NotificationService) {

    @GetMapping
    fun getAllNotifications(): ResponseEntity<List<Notification>> {
        val notifications = notificationService.getAllNotifications()
        return ResponseEntity(notifications, HttpStatus.OK)
    }

    @PostMapping
    fun createNotification(@RequestBody notificationInfo: Notification): ResponseEntity<Notification> {
        val createdNotification = notificationService.createNotification(notificationInfo)
        return ResponseEntity(createdNotification, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getNotificationById(@PathVariable id: Long): ResponseEntity<Notification> {
        val notificationInfo = notificationService.getNotificationById(id)
        return ResponseEntity(notificationInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateNotification(@PathVariable id: Long, @RequestBody updatedNotification: Notification): ResponseEntity<Notification> {
        val updatedInfo = notificationService.updateNotification(id, updatedNotification)
        return ResponseEntity(updatedInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteNotification(@PathVariable id: Long): ResponseEntity<Void> {
        notificationService.deleteNotification(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
