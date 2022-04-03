package com.project.stockexchangeappbackend.entity;
 import lombok;
import javax.persistence;
import java.time.OffsetDateTime;
@Entity
@Table(name = "SYSTEM_RESOURCES_MONITOR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SystemResourcesMonitor {

@Id
@GeneratedValue(generator = "SYSTEM_RESOURCES_MONITOR_SEQUENCE")
 private  Long id;

@Column(name = "TIMESTAMP", nullable = false, updatable = false)
 private  OffsetDateTime timestamp;

@Column(name = "CPU_USAGE", nullable = false, updatable = false)
 private  Double cpuUsage;

@Column(name = "MEMORY_USAGE", nullable = false, updatable = false)
 private  Double memoryUsage;

@JoinColumn(name = "MEMORY_USED", nullable = false, updatable = false, referencedColumnName = "ID")
 private  Long memoryUsed;


}