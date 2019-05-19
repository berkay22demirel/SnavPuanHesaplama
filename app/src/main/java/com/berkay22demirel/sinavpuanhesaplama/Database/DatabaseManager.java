package com.berkay22demirel.sinavpuanhesaplama.Database;

import android.content.Context;

import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.ALES;
import com.berkay22demirel.sinavpuanhesaplama.Model.DGS;
import com.berkay22demirel.sinavpuanhesaplama.Model.DUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.EKPSS;
import com.berkay22demirel.sinavpuanhesaplama.Model.EUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.KPSS;
import com.berkay22demirel.sinavpuanhesaplama.Model.TUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.YDS;
import com.berkay22demirel.sinavpuanhesaplama.Model.YKS;

import java.util.Arrays;
import java.util.List;

public class DatabaseManager {
    public static final long SUCCESSFUL = 1;
    public static final long ERROR = -1;
    private DatabaseManager databaseManager;
    private DatabaseHelper databaseHelper;
    private DatabaseUtils databaseUtils;
    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        databaseUtils = new DatabaseUtils(databaseHelper, this);
    }

    public Long put(Object data) {
        try {
            BaseDAO baseDAO = getDAO(data);
            if (baseDAO != null) {
                Long id = baseDAO.put(data);
                if (id != null && id.compareTo(0L) > 0) {
                    return id;
                }
            }
        } catch (Exception e) {
            return ERROR;
        }
        return ERROR;
    }

    public Object get(Long id, Class dataType) {
        try {
            BaseDAO baseDAO = getDAO(dataType);
            if (baseDAO != null) {
                return baseDAO.get(id);
            }
        } catch (Exception e) {
            return ERROR;
        }
        return ERROR;
    }

    public Long delete(Long id, Class dataType) {
        try {
            BaseDAO baseDAO = getDAO(dataType);
            if (baseDAO != null) {
                return baseDAO.delete(id);
            }
        } catch (Exception e) {
            return ERROR;
        }
        return ERROR;
    }

    public List<Object> getAll(Class dataType) {
        try {
            BaseDAO baseDAO = getDAO(dataType);
            if (baseDAO != null) {
                return baseDAO.getAll();
            }
        } catch (Exception e) {
            return Arrays.asList((Object) ERROR);
        }
        return Arrays.asList((Object) ERROR);
    }

    public List<Object> getAllExam() {
        try {
            return databaseUtils.getAllExam();
        } catch (Exception e) {
            return Arrays.asList((Object) ERROR);
        }
    }

    private BaseDAO getDAO(Object data) {
        if (data instanceof ALES) {
            return new AlesDAO(this, databaseUtils);
        } else if (data instanceof DGS) {
            return new DgsDAO(this, databaseUtils);
        } else if (data instanceof DUS) {
            return new DusDAO(this, databaseUtils);
        } else if (data instanceof EKPSS) {
            return new EkpssDAO(this, databaseUtils);
        } else if (data instanceof EUS) {
            return new EusDAO(this, databaseUtils);
        } else if (data instanceof KPSS) {
            return new KpssDAO(this, databaseUtils);
        } else if (data instanceof TUS) {
            return new TusDAO(this, databaseUtils);
        } else if (data instanceof YDS) {
            return new YdsDAO(this, databaseUtils);
        } else if (data instanceof YKS) {
            return new YksDAO(this, databaseUtils);
        }
        return null;
    }

}
