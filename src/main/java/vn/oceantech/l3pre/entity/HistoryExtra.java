package vn.oceantech.l3pre.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_extra")
public class HistoryExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "history_id")
    private Integer historyId;
    @Column(name = "drugs")
    private String drugs;
    @Column(name = "description_usage")
    private String descriptionUsage;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
