package com.remondis.resample;

import static com.remondis.resample.supplier.Suppliers.enumValueSupplier;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import com.remondis.resample.supplier.SupplierConfiguration;

@Service
@Import({
    SupplierConfiguration.class
})
public class SampleService {

  private ApplicationContext ctx;

  public SampleService() {
    super();
  }

  @Autowired
  public SampleService(ApplicationContext ctx) {
    super();
    this.ctx = ctx;
  }

  public <T> Sample<T> of(Class<T> type) {
    Sample<T> sample = getSample(type);
    if (nonNull(ctx)) {
      sample.useApplicationContext(ctx);
    }
    return sample;
  }

  private static <T> Sample<T> getSample(Class<T> type) {
    Sample<T> sample = Sample.of(type)
        .checkForNullFields()
        .useForEnum(enumValueSupplier())
        .useAutoSampling();
    return sample;
  }

}
