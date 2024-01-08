package com.titan.table;

import com.titan.product.ProductEntity;
import com.titan.product.ProductRepository;
import com.titan.table.request.TableRequest;
import com.titan.table.response.TableAddProductResponse;
import com.titan.table.response.TableCloseResponse;
import com.titan.table.response.TableResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(TableService.class);

    public List<TableEntity> getTableList() {
        return tableRepository.findAll();
    }

    public TableResponse createTable(TableRequest request) {
        var table = tableRepository.findTableByTableNumber(request.getTableNumber());
        if (table.isPresent())
            throw new IllegalArgumentException("Table with Number " +  request.getTableNumber() + " already exists");
        if (request.getPositionX() <= 0 || request.getPositionY() <= 0)
            throw new IllegalArgumentException("Positions cannot be negative: {X: " + request.getPositionX() + ", Y: " + request.getPositionY() + "}");
        var addedTable = tableRepository.save(
                new TableEntity(request.getTableNumber(), request.getNumberOfPeople(), request.getPositionX(), request.getPositionY())
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
                table.getId(),
                table.getOpenCosts(),
                table.getNumberOfPeople(),
                table.getProducts()
        );
    }

    public void deleteTable(Long id) {
        var table = tableRepository.findById(id).orElseThrow();
        tableRepository.deleteById(table.getId());
    }

    public TableAddProductResponse addProductToTable(Long id, List<ProductEntity> request) {
        var table = tableRepository.findById(id).orElseThrow();
        request.forEach(prod -> table.getProducts().add(prod));

        Double costs = 0.0;
        for (var product: request) {
            costs += product.getPrice();
        }
        table.setOpenCosts(costs);
        table.setOccupied(true);
        table.setOccupiedFrom(LocalDateTime.now());

        tableRepository.save(table);
        return new TableAddProductResponse(
                table.getId(),
                costs
        );
    }

    public List<ProductEntity> getProductsByTableId(Long id) {
        var products = tableRepository.findById(id).orElseThrow();
        log.info(products.toString());
        return products.getProducts();
    }

    public void updateTablesPositions(List<TableEntity> tables) {
        tableRepository.saveAll(tables);
    }
}
