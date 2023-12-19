package vn.oceantech.l3pre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.oceantech.l3pre.entity.Markdown;

public interface MarkdownRepo extends JpaRepository<Markdown, Integer> {
}
