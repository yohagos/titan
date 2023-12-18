package com.titan.table;

import com.titan.product.ProductEntity;
import com.titan.table.request.TableAddProductRequest;
import com.titan.table.request.TableRequest;
import com.titan.table.response.TableAddProductResponse;
import com.titan.table.response.TableCloseResponse;
import com.titan.table.response.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public List<TableEntity> getTableList() {
        return tableRepository.findAll();
    }

    public TableResponse createTable(TableRequest request) {
        var table = tableRepository.findTableByTableNumber(request.getTableNumber());
        if (table.isPresent())
            throw new IllegalArgumentException("Table with Number " +  request.getTableNumber() + " already exists");
        var addedTable = tableRepository.save(
                new TableEntity(request.getTableNumber(), request.getNumberOfPeople())
        );
        return new TableResponse(
                addedTable.getId(), addedTable.getTableNumber(), addedTable.getNumberOfPeople()
        );
    }


    public void occupyTableNow(Long id) {
        var table = tableRepository.findById(id).orElseThrow();
        table.setOccupied(true);
        table.setOccupiedFrom(LocalDateTime.now());
        tableRepository.save(table);
    }


    public TableResponse editTable(Long id, TableRequest request) {
        var table = tableRepository.findById(id).orElseThrow();
        if (table.isOccupied())
            throw new IllegalArgumentException("Table is currently occupied, please wait till it is not anymore");
        Optional.ofNullable(request.getTableNumber())
                .filter(tableNumber -> tableNumber > 0)
                .ifPresent(table::setTableNumber);
        Optional.ofNullable(request.getNumberOfPeople())
                .filter(numberOfPeople -> numberOfPeople > 0)
                .ifPresent(table::setNumberOfPeople);
        tableRepository.save(table);
        return new TableResponse(
                table.getId(),
                table.getTableNumber(),
                table.getNumberOfPeople()
        );
    }

    public TableCloseResponse closeTable(Long id) {
        var table = tableRepository.findById(id).orElseThrow();
        var responseInformation = table;
        if (table.getOpenCosts() <= 0.0)
            throw new IllegalArgumentException(
                    "Current costs of table is 0.0 or it is not occupied. " +
                    "Therefore it cannot be closed");
        table.setOccupied(false);
        table.setOccupiedTill(LocalDateTime.now());
        table.setOpenCosts(0.0);
        table.setProducts(List.of());
        table.setNumberOfPeople(0);

        return new TableCloseResponse(
                responseInformation.getId(),
                responseInformation.getOpenCosts(),
                responseInformation.getNumberOfPeople(),
                responseInformation.getProducts()
        );
    }

    public void deleteTable(Long id) {
        var table = tableRepository.findById(id).orElseThrow();
        tableRepository.deleteById(table.getId());
    }

    public TableAddProductResponse addProductToTable(TableAddProductRequest request) {
        var table = tableRepository.findById(request.getId()).orElseThrow();
        request.getProducts().forEach(prod -> table.getProducts().add(prod));

        Double costs = 0.0;
        for (var product: request.getProducts()) {
            costs += product.getPrice();
        }
        table.setOpenCosts(costs);
        tableRepository.save(table);
        return new TableAddProductResponse(
                table.getId(),
                table.getProducts(),
                costs
        );
    }
}
