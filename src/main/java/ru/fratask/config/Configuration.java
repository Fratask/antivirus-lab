package ru.fratask.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.fratask.model.State;

@Data
@Component
public class Configuration {
    private State state = State.ENABLE;
}
