///*
//package com.example.chatapp.Extras;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
//
//    private YourAdapter adapter;
//
//    public SwipeToDeleteCallback(YourAdapter adapter) {
//        super(0, ItemTouchHelper.RIGHT); // Only allow right swipe
//        this.adapter = adapter;
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false; // We don't need to handle move events
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int position = viewHolder.getAdapterPosition();
//        adapter.deleteItem(position);
//    }
//
//    @Override
//    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            View itemView = viewHolder.itemView;
//
//            // Calculate the position and dimensions of the delete icon and text
//            int deleteIconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
//            int deleteIconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
//            int deleteIconBottom = deleteIconTop + deleteIcon.getIntrinsicHeight();
//            int deleteIconRight = itemView.getRight() - deleteIconMargin;
//            int deleteIconLeft = deleteIconRight - deleteIcon.getIntrinsicWidth();
//
//            // Draw the red background
//            c.drawRect(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom(), backgroundPaint);
//
//            // Draw the delete icon
//            deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
//            deleteIcon.draw(c);
//
//            // Draw the "DELETE" text
//            c.drawText("DELETE", deleteIconLeft - 200, itemView.getTop() + itemView.getHeight() / 2 + 15, textPaint);
//        }
//
//        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//    }
//}*/
