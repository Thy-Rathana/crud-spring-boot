package com.example.crudDemo.Modules.Crud;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CrudService {
  protected Integer limit = 10;
  private String relationsKey = "relations";
  private String filterOptionKey = "filter_options";
  private String countsKey = "counts";

  //    protected Map<String, Object> prepareOptions(Map<String, Object> options) {
  //        if (options == null || options.isEmpty()) {
  //            return Map.of(
  //                    "filters", new HashMap<String, Object>(),
  //                    "relations", new HashMap<String, Object>(),
  //                    "counts", new HashMap<String, Object>(),
  //                    "fields", Arrays.asList("*"),
  //                    "page", 1,
  //                    "limit", this.limit,
  //                    "sorts", new HashMap<String, Object>(),
  //                    "search", null,
  //                    "search_fields", new ArrayList<String>()
  //            );
  //        }
  //        setFilters(
  //                getFilterFields(options),
  //                getFilterOptions(options)
  //        );
  //        return Map.of(
  //                "filters", getFilters(),
  //                "relations", getRelations(options),
  //                "counts", getCounts(options),
  //                "fields", getSelectedFields(options),
  //                "page", (int) (options.getOrDefault("page", 1)),
  //                "limit", (int) (options.getOrDefault("limit", this.limit)),
  //                "sorts", getSorts(options),
  //                "search", options.getOrDefault("search", null),
  //                "search_fields", options.getOrDefault("search_fields", searchable())
  //        );
  //    }

  protected ArrayList<String> getFilterFields(Map<String, String> options) {
    ArrayList<String> filterFields = new ArrayList<String>();

    for (Map.Entry<String, String> entry : options.entrySet()) {
      if (!Arrays.asList(
              "_token",
              "_method",
              "sorts",
              "limit",
              "page",
              "fields",
              "search",
              "search_fields",
              "no_pagination",
              this.relationsKey,
              this.countsKey,
              this.filterOptionKey
            )
            .contains(entry.getKey())) {
        filterFields.add(entry.getKey());
      }
    }

    return filterFields;
  }

  protected Map<String, String> getFilterOptions(Map<String, String> options) {
     Map<String, String> filterOptions = new HashMap<String, String>();

    if (options.get(this.filterOptionKey) == null) {
      return filterOptions;
    }

    for (String filterOption : options.get(this.filterOptionKey).split(",")) {
      String[] arrFilterOption = filterOption.split(":");
      if (arrFilterOption.length > 1) {
           filterOptions.put(arrFilterOption[0], arrFilterOption[1]);
      }
    }

//    System.out.println(filterOptions);
    return filterOptions;
  }

  //  protected function setFilters(array $fields = [], array $options = []): void
  //        {
  //        $defaultFilters = $this->defaultFilters();
  //        $filters = collect($defaultFilters);
  //        $filterable = $this->filterable();
  //
  //        foreach ($fields as $field => $value) {
  //        if ($this->hasValidFilterableField($field)) {
  //        $filters->push([
  //        'field' => $filterable ? $filterable[$field] : $field,
  //        'value' => $this->value($value),
  //        'operator' => $this->getOperator($options[$field] ?? '='),
  //        ]);
  //        }
  //        }
  //
  //        $this->filters = $filters;
  //        }

//  protected Map<String, String> setFilters(ArrayList<String> fields, Map<String, String> options) {
//    Map<String, String> defaultFilters = defaultFilters();
//    Map<String, String> filters = new HashMap<String, String>();
//    Map<String, String> filterable = filterable();
//
//    for (String field : fields) {
//      //    if (hasValidFilterableField(field)) {
//      filters.put(filterable.getOrDefault(field, field), value(options.getOrDefault(field, "")));
//      //    }
//    }
//
//    return filters;
//  }
}
