package ENSF480TermProject.backend.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ENSF480TermProject.backend.enums.TransactionType;
import ENSF480TermProject.backend.interfaces.TransactionStrategy;

@Component
public class TransactionStrategyRegistry {

    private final Map<TransactionType, TransactionStrategy> strategies = new HashMap<>();

    @Autowired
    public TransactionStrategyRegistry(List<TransactionStrategy> strategyList) {
        for (TransactionStrategy strategy : strategyList) {
            strategies.put(strategy.getType(), strategy);
        }
    }

    public TransactionStrategy getStrategy(TransactionType type) {
        return Optional.ofNullable(strategies.get(type)).orElseThrow(() -> new IllegalArgumentException("No strategy found for type: " + type.name() + " " + strategies.toString()));
    }
}

