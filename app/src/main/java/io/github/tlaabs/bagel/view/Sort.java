package io.github.tlaabs.bagel.view;

import java.util.Collections;
import java.util.List;

import io.github.tlaabs.bagel.api.Repo;

public class Sort {
    public static void sort(List<Repo> repos){
        Collections.sort(repos,(Repo repo1, Repo repo2) -> repo2.getStargazers_count() - repo1.getStargazers_count());
    }
}
