package com.rahul.schemes;

import java.util.List;

public interface Scheme_details_Interface {
public int add_scheme(Scheme_Model scm);
public int edit_scheme(Scheme_Model scm);
public int delete_scheme(int sid);
public List<Scheme_Model> getallschemes();
public List<Scheme_Model> get_Scheme_By_Name(String name);
public Scheme_Model get_Scheme(String name);
public Scheme_Model get_Scheme_by_id();
}
