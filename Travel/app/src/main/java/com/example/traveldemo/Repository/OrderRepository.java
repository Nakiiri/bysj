package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.OrderDao;
import com.example.traveldemo.Database.OrderDatabase;
import com.example.traveldemo.Entity.Order;

import java.util.List;

public class OrderRepository {
    private OrderDao orderDao;

    public OrderRepository(Context context) {
        OrderDatabase orderDatabase = OrderDatabase.getOrderDatabase(context.getApplicationContext());
        orderDao = orderDatabase.getOrderDao();
    }

    public LiveData<List<Order>> findOrderByUserState12(String pattern){
        return orderDao.findOrderByUserState12(pattern);
    }
    public LiveData<List<Order>>findOrderByUserState0(String pattern){
        return orderDao.findOrderByUserState0(pattern);
    }
    public LiveData<List<Order>>findOrderByAgencyLogin(String pattern){
        return orderDao.findOrderByAgencyLogin(pattern);
    }
    public LiveData<List<Order>>findOrderByUserAndPlan(String pattern,int pattern2){
        return orderDao.findOrderByUserAndPlan(pattern,pattern2);
    }
    public LiveData<List<Order>>findOrderByID(int pattern){
        return orderDao.findOrderByID(pattern);
    }
    public LiveData<List<Order>>findOrderByAgency(String pattern){
        return orderDao.findOrderByAgency(pattern);
    }
    public LiveData<List<Order>>findOrderByPlan(int pattern){
        return orderDao.findOrderByPlan(pattern);
    }




    public void insertOrder(Order...orders){
        new InsertAsyncTask(orderDao).execute(orders);
    }
    public void deleteOrder(Order...orders){
        new DeleteAsyncTask(orderDao).execute(orders);
    }
    public void updateState1(Order...orders){
        new UpdateState1AsyncTask(orderDao).execute(orders);
    }
    public void updateState2(Order...orders){
        new UpdateState2AsyncTask(orderDao).execute(orders);
    }
    public void updateAssess(Order...orders){
        new UpdateAssessAsyncTask(orderDao).execute(orders);
    }



    static class InsertAsyncTask extends AsyncTask<Order,Void,Void>{
        private OrderDao orderDao;

        public InsertAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insertOrder(orders);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Order,Void,Void>{
        private OrderDao orderDao;

        public DeleteAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.deleteOrder(orders);
            return null;
        }
    }

    static class UpdateState1AsyncTask extends AsyncTask<Order,Void,Void>{
        private OrderDao orderDao;

        public UpdateState1AsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.updateOrderState1(orders[0].getOrder_id());
            return null;
        }
    }

    static class UpdateState2AsyncTask extends AsyncTask<Order,Void,Void>{
        private OrderDao orderDao;

        public UpdateState2AsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.updateOrderState2(orders[0].getOrder_id());
            return null;
        }
    }

    static class UpdateAssessAsyncTask extends AsyncTask<Order,Void,Void>{
        private OrderDao orderDao;

        public UpdateAssessAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.updateOrderAssess(orders[0].getOrder_assess(),orders[0].getOrder_id());
            return null;
        }
    }


}
