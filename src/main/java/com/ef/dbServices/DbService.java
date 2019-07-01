package com.ef.dbServices;

import java.util.List;

public interface DbService<REQUEST> {

   void execute(List<REQUEST> request);
}
