package ru.brambrulet.service.memory;

import ru.brambrulet.entity.CustomerEntity;
import ru.brambrulet.service.iface.CustomerService;

public class CustomerServiceInMemory extends ServiceInMemory<CustomerEntity> implements CustomerService {
}
