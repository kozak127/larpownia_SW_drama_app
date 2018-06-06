package pl.kozak127.swdramatic.domain.resource;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceType type;
}
